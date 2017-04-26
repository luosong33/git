package com.jumore.jmdata.common.requests;

import com.jumore.jmdata.common.exceptions.BaseException;
import com.jumore.jmdata.enums.BaseExceptionEnum;

/** 
* 响应格式
* @author: panweiqiang
* @since: 2016年7月1日  上午9:59:45
* @history:
*/
public class CommResponse {

    /**
     * 返回状态
     */
    private String name;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回内容
     */
    private Object data;

    public CommResponse() {
    }

    public CommResponse(Object data) {
        this.data = data;
    }

    public CommResponse(String message) {
        this.message = message;
    }

    public CommResponse(Object data, String message) {
        this.data = data;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    /** 
     * 功能说明：返回一个请求成功的响应模板
     * 
     * @param data
     * @return 
     * CommResponse
     */
    public static CommResponse success() {
        CommResponse response = new CommResponse();
        response.setMessage(RESULT_MSG_SUCCESS);
        return response;
    }

    /** 
     * 功能说明：返回一个请求成功的响应模板
     * 
     * @param data
     * @return 
     * CommResponse
     */
    public static CommResponse success(Object data) {
        CommResponse response = new CommResponse(data);
        response.setMessage(RESULT_MSG_SUCCESS);
        return response;
    }

    /** 
     * 功能说明：返回一个请求失败的响应模板
     * 
     * @param baseExceptionEnum 异常参数不能为空
     * @return 
     * CommResponse
     */
    public static CommResponse failure(BaseExceptionEnum baseExceptionEnum) {
        CommResponse response = new CommResponse();
        response.setName(baseExceptionEnum.getCode());
        response.setMessage(baseExceptionEnum.getMsg());
        return response;
    }

    /** 
     * 功能说明：返回一个请求失败的响应模板
     * 
     * @param message 错误信息
     * @return 
     */
    public static CommResponse failure(String message) {
        CommResponse response = new CommResponse();
        response.setName(BaseExceptionEnum.PARAM_ERROR.getCode());
        response.setMessage(message);
        return response;
    }

    /** 
     * 功能说明：返回一个请求失败的响应模板
     * 
     * @param e 异常参数不能为空
     * @return 
     * CommResponse
     */
    public static CommResponse failure(Exception e) {
        CommResponse response = new CommResponse();
        response.setMessage(RESULT_MSG_FAILURE);
        return response;
    }

    /** 
     * 功能说明：返回一个请求失败的响应模板
     * 
     * @param e 异常参数不能为空
     * @return 
     * CommResponse
     */
    public static CommResponse failure(BaseException e) {
        CommResponse response = new CommResponse();
        if (e != null) {
            response.setName(e.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 错误返回结果msg
     */
    public static final String RESULT_MSG_FAILURE = "操作失败";

    /**
     * 正确返回结果msg
     */
    public static final String RESULT_MSG_SUCCESS = "操作成功";
}
