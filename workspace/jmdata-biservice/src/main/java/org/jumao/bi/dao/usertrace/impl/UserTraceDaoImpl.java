package org.jumao.bi.dao.usertrace.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.jumao.bi.dao.usertrace.IUserTraceDao;
import org.jumao.bi.entites.usertrace.UserTraceInfo;
import org.jumao.commons.frameworks.jmframework.commutil.MRConstants;
import org.jumao.commons.frameworks.jmframework.hbaseutil.dao.IHBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("userTraceDao")
public class UserTraceDaoImpl implements IUserTraceDao {

    @Autowired
    IHBaseDao hbasedao;

    public static final String USER_LOGINOUT_TAB = "jmbi:userLogInOut";
    public static final byte[] CF_NAME = Bytes.toBytes(MRConstants.TAB_CF);
    
    public void addUserTraceInfo(List<UserTraceInfo> userTraces) throws IOException {
        List<Put> puts = new ArrayList<Put>();
        for (UserTraceInfo userTrace : userTraces) {
            String bizCode = userTrace.getBizCode();
            String loginTime = userTrace.getLoginTime();
            String userAgent = userTrace.getUserAgent();
            Put put = new Put(Bytes.toBytes(bizCode + loginTime));
            put.addColumn(CF_NAME, "bizCode".getBytes(), bizCode.getBytes());
            put.addColumn(CF_NAME, "loginTime".getBytes(), loginTime.getBytes());
            put.addColumn(CF_NAME, "userAgent".getBytes(), userAgent.getBytes());
            puts.add(put);
        }
        
        if (!puts.isEmpty()) {
            hbasedao.save(puts, USER_LOGINOUT_TAB);
        }
    }

}