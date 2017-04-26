package org.jumao.bi.service.impl.usertrace;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.dao.usertrace.IUserTraceDao;
import org.jumao.bi.entites.CommonResponse;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.usertrace.UserTraceInfo;
import org.jumao.bi.entites.usertrace.UserTraces;
import org.jumao.bi.service.usertrace.IUserTraceSvc;
import org.jumao.bi.utis.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTraceSvcImpl implements IUserTraceSvc {

    @Autowired
    private IUserTraceDao userTraceDao;

    private Logger   logger = Logger.getLogger(this.getClass());

    public Response addUserTraceInfo(UserTraces userTraces) {
        LogUtils.writeLogs(logger, "add user trace infomation from ES start.");
        CommonResponse response = new CommonResponse();
        try {
            userTraceDao.addUserTraceInfo(userTraces.getList());
            response.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        } catch (IOException e) {
            response.setStatus(new ResponseResult(ServiceConst.SERVER_ERROR_CODE, ServiceConst.ERROR_MSG + e.getMessage()));
            LogUtils.writeLogs(logger, "add user trace infomation error: " + e.getMessage());

            return Response.serverError().entity(response).build();
        }

        LogUtils.writeLogs(logger, "end user trace infomation.");
        return Response.ok().entity(response).build();

    }

}