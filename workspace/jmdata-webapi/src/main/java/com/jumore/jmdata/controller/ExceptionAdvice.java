package com.jumore.jmdata.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jumore.dove.common.BusinessException;
import com.jumore.dove.common.log.LogHelper;
import com.jumore.jmdata.common.exceptions.AuthorizedException;
import com.jumore.jmdata.common.exceptions.BizException;
import com.jumore.jmdata.common.exceptions.RemoteGenericException;
import com.jumore.jmdata.common.exceptions.ValidationException;
import com.jumore.jmdata.common.requests.CommResponse;

/** 
* 异常处理切面
* @author: panweiqiang
* @since: 2016年7月4日  下午1:48:15
* @history:
*/
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

    private static final LogHelper logger = LogHelper.getLogger(ExceptionAdvice.class);

    /**
     * 远程调用异常
     *
     * @param e
     * @return 
     * @history
     */
    @ExceptionHandler(RemoteGenericException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommResponse handleRemoteGenericException(RemoteGenericException e) {
        logger.getBuilder().error("远程服务调用异常", e);
        return CommResponse.failure(e);
    }

    /**
     *校验异常 
     *
     * @param e
     * @return 
     * @history
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommResponse handleValidationException(ValidationException e) {
        logger.getBuilder().warn("校验失败", e);
        return CommResponse.failure(e);
    }

    /**
     * 业务异常
     *
     * @param e
     * @return
     * @history
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommResponse handleBizException(BizException e) {
        logger.getBuilder().error("业务异常", e);
        return CommResponse.failure(e);
    }

    /**
     * 业务异常
     *
     * @param e
     * @return
     * @history
     */
    @ExceptionHandler(AuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommResponse handleBizException(AuthorizedException e) {
        logger.getBuilder().error("用户权限异常", e);
        return CommResponse.failure(e);
    }

    /**
     * 特殊字符校验异常
     *
     * @param e
     * @return 
     * @history
     */
    @ExceptionHandler(BusinessException.class)
    public CommResponse handleBizException(BusinessException e) {
        logger.getBuilder().warn("特殊字符校验异常", e);
        return CommResponse.failure("输入内容包含非法字符,请重新输入!");
    }

    /**
     * 未知异常
     *
     * @param e
     * @return 
     * @history
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommResponse handleException(Exception e) {
        logger.getBuilder().error("服务运行异常", e);
        return CommResponse.failure(e);
    }

}
