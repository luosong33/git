package org.jumao.bi.dao.usertrace.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.jumao.bi.dao.usertrace.IUserTraceDao;
import org.jumao.bi.entites.ParamBean;
import org.jumao.bi.entites.usertrace.BrowseBean;
import org.jumao.bi.entites.usertrace.LoginBean;
import org.jumao.bi.entites.usertrace.UserTraceInfo;
import org.jumao.commons.frameworks.jmframework.commutil.MRConstants;
import org.jumao.commons.frameworks.jmframework.hbaseutil.dao.IHBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository("userTraceDao")
public class UserTraceDaoImpl  implements IUserTraceDao {

    @Autowired
    IHBaseDao hbasedao;
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    

    public static final String USER_LOGINOUT_TAB = "jmbi:userTraces";
    public static final byte[] CF_NAME = Bytes.toBytes(MRConstants.TAB_CF);
    
    public void addUserTraceInfo(List<UserTraceInfo> userTraces) throws IOException {
        List<Put> puts = new ArrayList<Put>();
        for (UserTraceInfo userTrace : userTraces) {
            String bizCode = userTrace.getBizCode();
            String loginTime = userTrace.getLoginTime();
            String deviceType = userTrace.getDeviceType();
            String browseType = userTrace.getBrowseType();
            Put put = new Put(Bytes.toBytes(bizCode + loginTime));
            put.addColumn(CF_NAME, "bizCode".getBytes(), bizCode.getBytes());
            put.addColumn(CF_NAME, "loginTime".getBytes(), loginTime.getBytes());
            put.addColumn(CF_NAME, "deviceType".getBytes(), deviceType.getBytes());
            put.addColumn(CF_NAME, "browseType".getBytes(), browseType.getBytes());
            puts.add(put);
        }
        
        if (!puts.isEmpty()) {
            hbasedao.save(puts, USER_LOGINOUT_TAB);
        }
    }

    public List<BrowseBean> getUserBrowserInfo(ParamBean param) {
        String sql="select browsetype, count(browsetype) as nums from jmbi_user_traces where "
                + "bizcode='" + param.getPlatform()
                + "' and logintime between '" + param.getStartDate()
                + "' and '" + param.getEndDate()
                + "' group by browsetype";
    
        RowMapper<BrowseBean> rowMapper = new BeanPropertyRowMapper<BrowseBean>(BrowseBean.class);
        List<BrowseBean> browses = jdbcTemplate.query(sql, rowMapper);
        
        return browses;
        
    }

    public List<LoginBean> getUserLoginInfo(ParamBean param) {
        String sql="select STRLEFT(logintime, 8) as login , count(STRLEFT(logintime, 8)) as nums from jmbi_user_traces where "
                + "bizcode='" + param.getPlatform()
                + "' and logintime between '" + param.getStartDate()
                + "' and '" + param.getEndDate()
                + "' group by  login order by login";
        RowMapper<LoginBean> rowMapper = new BeanPropertyRowMapper<LoginBean>(LoginBean.class);
        List<LoginBean> logins = jdbcTemplate.query(sql, rowMapper);
        
        return logins;
    }

}
