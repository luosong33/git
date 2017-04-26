package com.jumore.jmdata.common.requests;

import com.jumore.jmdata.common.exceptions.BaseException;
import com.jumore.jmdata.result.ResponseDTO.ResponseCode;

public class CommResponseUtil {

    /** 
     * 功能说明：根据错误类别返回错误码
     * 
     * @param e
     * @return 
     * Integer
     */
    public static Integer getStatusCode(BaseException e) {
        StatusCodeEnum statusCode = StatusCodeEnum.INVALID_REQUEST;
        if (e.getCode() == null) {
            return statusCode.getCode();
        }
        ResponseCode code = ResponseCode.valueOf(e.getCode());
        switch (code) {
            case SUCCESS:
                statusCode = StatusCodeEnum.OK;
                break;
            case ERROR:
                statusCode = StatusCodeEnum.INVALID_REQUEST;
                break;
            case EXCEPTION:
                statusCode = StatusCodeEnum.INTERNAL_SERVER_ERROR;
                break;
            case UNSUPPORT:
                statusCode = StatusCodeEnum.FORBIDDEN;
                break;
            case NO_DATA:
                statusCode = StatusCodeEnum.GONE;
                break;
            default:
                statusCode = StatusCodeEnum.INVALID_REQUEST;
                break;
        }
        return statusCode.getCode();
    }
}
