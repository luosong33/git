package com.jumore.jmbi.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.jumore.dove.common.log.LogHelper;
import com.jumore.jmbi.common.exceptions.BizException;
import com.jumore.jmbi.common.util.Consts;
import com.jumore.jmbi.common.util.JsonUtil;

public class CommonResponse {

    private static LogHelper logHelper = LogHelper.getLogger(CommonResponse.class);

    /**
     * 数据写入相应中
     * 
     * @param response
     * @param write
     */
    public static void writeResponse(HttpServletResponse response, String write) {
        PrintWriter pw = null;
        try {
            response.setHeader("Content-type", "text/html;charset=utf-8");
            pw = response.getWriter();
            pw.write(write);
            pw.flush();
        } catch (IOException e) {
            logHelper.getBuilder().error("Response write param = " + write);
            logHelper.getBuilder().error("Response write Excetpion: " + e);
        } finally {
            if (null != pw) {
                pw.close();
                pw = null;
            }
        }
    }

    public static <T> void writeResponse(HttpServletResponse response, List<T> list, int total) {
        PrintWriter pw = null;
        String json = null;
        try {
            JSONObject obj = new JSONObject();
            String jso = JsonUtil.offerJson(list);
            obj.put("rows", jso);
            obj.put("total", total);

            response.setHeader("Content-type", "text/plain;charset=UTF-8");// Constants.CONTENT_TYPE);
            pw = response.getWriter();
            pw.write(obj.toString());
            pw.flush();
        } catch (IOException e) {
            logHelper.getBuilder().error("Response write param = " + json);
            logHelper.getBuilder().error("Response write Excetpion: ", e);
        } finally {
            if (null != pw) {
                pw.close();
                pw = null;
            }
        }
    }

    /**
     * 返回操作成功的响应
     * 
     * @param response
     * @param write
     */
    public static void writeSuccessResponse(HttpServletResponse response, Map<String, Object> map) {
        if (map == null) {
            map = new HashMap<String, Object>();
        }
        map.put(Consts.RESULT_CODE, Consts.result_Code_Right);
        map.put(Consts.RESULT_MSG, Consts.result_Msg_Right);
        String write = JSON.toJSONString(map);
        writeResponse(response, write);
    }

    /**
     * 返回操作成功的响应
     * 
     * @param response
     * @param write
     */
    public static void writeErrorResponse(HttpServletResponse response, Exception e) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (e instanceof BizException) {
            BizException ex = (BizException) e;
            map.put(Consts.RESULT_CODE, ex.getErrorCode());
            map.put(Consts.RESULT_MSG, ex.getMessage());
        } else {
            map.put(Consts.RESULT_MSG, Consts.result_Msg_Error);
            map.put(Consts.RESULT_CODE, Consts.result_Code_Error);
        }
        String write = JSON.toJSONString(map);
        writeResponse(response, write);
    }
}
