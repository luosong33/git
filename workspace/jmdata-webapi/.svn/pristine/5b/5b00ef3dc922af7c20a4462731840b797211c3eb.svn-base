/**
 * 
 */
package com.jumore.jmdata.common.exceptions;

import com.jumore.jmdata.enums.BaseExceptionEnum;

/**
*项目异常基类
* @author: luochao
* @since: 2016年7月4日  下午7:05:09
* @history:
*/
public class BaseException extends Exception {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -758729270479446205L;

    /**错误编码*/
    private String            code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BaseException(BaseExceptionEnum baseExceptionEnum) {
        super(baseExceptionEnum.getMsg());
        this.code = baseExceptionEnum.getCode();
    }

    public BaseException(BaseExceptionEnum exceptionEnum, Throwable cause) {
        super(exceptionEnum.getMsg(), cause);
        this.code = exceptionEnum.getCode();
    }

}
