package org.jumao.jobs.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.jumao.jobs.IElasticSearchJobs;
import org.jumao.mails.mainmail.MailMessage;
import org.jumao.spider.beans.AgentBean;
import org.jumao.spider.beans.UserTraceInfo;
import org.jumao.spider.utils.DateUtils;
import org.jumao.spider.utils.HttpUtil;
import org.jumao.spider.utils.UserAgentUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Component("elasticsearchjobs")
public class ElasticSearchJobsImpl extends MailMessage implements IElasticSearchJobs {

    @Value("${biservice.usertrace.url}")
    private String              userTraceUrl;

    @Value("${es.size}")
    private int                 esSize;

    @Value("${es.cluster.name}")
    private String              clusterName;                           // 实例名称

    @Value("${es.ip}")
    private String              ip;

    @Value("${es.port}")
    private int                 port;                                  // 端口

    private static final String ES_USER_TRACE_INDEX = "jumore_dev_log";
    private static final String ES_USER_TRACE_TYPE  = "messages";

    public void exportUserLoginData() throws IOException {

        // 查询尚未处理的用户轨迹数据
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("bi-action-type", "userLogin"));
        boolQueryBuilder.must(QueryBuilders.matchQuery("process", "false"));
       
        TransportClient client = this.getTransportClient();
        SearchResponse response = client.prepareSearch(ES_USER_TRACE_INDEX).setTypes(ES_USER_TRACE_TYPE).setQuery(boolQueryBuilder)
                .setSize(esSize).execute().actionGet();

        //查询结果处理
        SearchHit[] hits = response.getHits().getHits();
        List<UserTraceInfo> userTraceList = new ArrayList<UserTraceInfo>();
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();

        for (SearchHit hit : hits) {
            processUserTrace(client, userTraceList, bulkRequestBuilder, hit);
        }

        // 同步数据到HBASE
        JSONObject jObj = new JSONObject();
        jObj.put("list", userTraceList);
        HttpUtil.doPost(userTraceUrl, jObj);

        // 修改已经处理过的状态数据
        if (bulkRequestBuilder.numberOfActions() > 0) {
            BulkResponse bulkResponse = bulkRequestBuilder.execute().actionGet();
            if (bulkResponse.hasFailures()) {
                super.sendEmail("Elastic Search update process status failed", bulkResponse.buildFailureMessage());
            }

        }

        // 关闭连接
        client.close();
    }

    private void processUserTrace(TransportClient client, List<UserTraceInfo> userTraceList, BulkRequestBuilder bulkRequestBuilder,
            SearchHit hit) throws IOException {
        UpdateRequestBuilder updateRequest;
        String value = hit.getSource().get("bi-action-value").toString();
        JSONObject obj = JSON.parseObject(value.split("=")[1]);

        boolean isInvalidData = (null == obj.getString("loginTime"))
               || (null == obj.getBigInteger("userId"));
        if (!isInvalidData) {
            String loginTime = obj.getString("loginTime").toString();
            String loginTimeStamp = DateUtils.stampToDate(obj.getString("loginTime").toString());
            String bizCode = obj.getString("bizCode").toString();
            String userId = obj.getBigInteger("userId").toString();
           
            String isOverseas = obj.getString("isOverseas").toString();
            
            bizCode = (bizCode.length() < 6) ? bizCode + "00" : bizCode;
            
    
            UserTraceInfo traceInfo = new UserTraceInfo();
            traceInfo.setKey(bizCode + loginTime);// 避免hbase rowkey重复
            traceInfo.setBizCode(bizCode);
            traceInfo.setLoginTime(loginTimeStamp);
            traceInfo.setUserId(userId);
            if(null != obj.getBigInteger("companyId")) {//有些用户没有绑定公司
                String companyId = obj.getBigInteger("companyId").toString();
                traceInfo.setCompanyId(companyId);
            }
            
            traceInfo.setIsOverseas(isOverseas);
            if (null != obj.getString("userAgent")) {
                String userAgent = obj.getString("userAgent").toString();
                AgentBean agent = UserAgentUtil.getUserAgentInfo(userAgent);
                if (null != agent) {
                    traceInfo.setDeviceType(agent.getDeviceType());
                    traceInfo.setBrowseType(agent.getBrowserType());
                }
            }
            userTraceList.add(traceInfo);
        }
        // 准备修改处理状态
        updateRequest = client.prepareUpdate(ES_USER_TRACE_INDEX, ES_USER_TRACE_TYPE, hit.getId()).setDoc(
                XContentFactory.jsonBuilder().startObject().field("process", "true").endObject());
        bulkRequestBuilder.add(updateRequest);
    }

    public TransportClient getTransportClient() throws UnknownHostException {

        Settings settings = Settings.settingsBuilder().put("cluster.name", clusterName).put("client.transport.sniff", true).build();
        TransportClient client = TransportClient.builder().settings(settings).build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip), port));

        return client;
    }

    public String getUserTraceUrl() {
        return userTraceUrl;
    }

    public void setUserTraceUrl(String userTraceUrl) {
        this.userTraceUrl = userTraceUrl;
    }

    public int getEsSize() {
        return esSize;
    }

    public void setEsSize(int esSize) {
        this.esSize = esSize;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
