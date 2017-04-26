package com.jumore.jmdata.controller.report;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jumore.jmdata.model.param.report.ReportParam;
import com.jumore.jmdata.model.vo.common.JsonpRespModel;
import com.jumore.jmdata.model.vo.common.PageRespModel;
import com.jumore.jmdata.model.vo.common.RespModel;
import com.jumore.jmdata.service.common.IFileService;
import com.jumore.jmdata.service.report.IReportService;

/**
 * Created by root on 11/1/16.
 */
@Controller
@RequestMapping(value = "/v1/report")
public class ReportController {

    private final static String TABLE_NAME = "JSJ_REPORT";

    @Resource
    private IReportService reportService;

    @Resource
    private IFileService fileService;

    /**
     *  前台-->分析报告
     * @param param
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public PageRespModel list(ReportParam param) {
        return reportService.getReportAndFileList(param);
    }

    /**
     * 下载，收藏，点赞
     * @param userId
     * @param reportId
     * @param flag
     * @return
     */
    @RequestMapping("/operate")
    @ResponseBody
    public RespModel operate(String userId, String reportId, String flag) throws ParseException {
    	JsonpRespModel model = new JsonpRespModel();
        Map map = new HashMap();
        if(StringUtils.isNotEmpty(userId)) {
            map = reportService.operate(userId, reportId, flag);
        }
        model.setRows(map);
        return model;
    }
    
    
    /**
     * 下载，收藏，点赞 供中台使用
     * @param userId
     * @param reportId
     * @param flag
     * @return
     */
    @RequestMapping("/operateJsonp")
    @ResponseBody
    public JSONPObject operateJsonp(String userId, String reportId, String flag,String callback) throws ParseException {
    	RespModel model = new RespModel();
        Map map = new HashMap();
        if(StringUtils.isNotEmpty(userId)) {
            map = reportService.operate(userId, reportId, flag);
        }
        model.setRows(map);
        JSONPObject jsob=new JSONPObject(callback, model);
        return jsob;
    }
    
}
