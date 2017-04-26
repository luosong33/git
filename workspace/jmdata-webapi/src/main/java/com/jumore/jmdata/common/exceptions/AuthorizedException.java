/**
 * 
 */
package com.jumore.jmdata.common.exceptions;

import com.jumore.jmdata.enums.BaseExceptionEnum;

/**
* 用户权限异常
* @author: luochao
* @since: 2016年7月4日  下午7:05:09
* @history:
*/
public class AuthorizedException extends BaseException {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -758729270479446205L;

    public AuthorizedException(String code, String message) {
        super(code, message);
    }

    public AuthorizedException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public AuthorizedException(BaseExceptionEnum baseExceptionEnum) {
        super(baseExceptionEnum);
    }

    public AuthorizedException(BaseExceptionEnum baseExceptionEnum, Throwable cause) {
        super(baseExceptionEnum, cause);
    }

}
