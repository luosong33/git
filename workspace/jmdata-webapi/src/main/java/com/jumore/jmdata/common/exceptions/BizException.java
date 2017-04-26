/**
 * 
 */
package com.jumore.jmdata.common.exceptions;

import com.jumore.jmdata.enums.BaseExceptionEnum;

/**
* 业务异常类
* @author: luochao
* @since: 2016年7月4日  下午6:02:00
* @history:
*/
public class BizException extends BaseException {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 2653145822151544474L;

    public BizException(String code, String message) {
        super(code, message);
    }

    public BizException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public BizException(BaseExceptionEnum baseExceptionEnum) {
        super(baseExceptionEnum);
    }

    public BizException(BaseExceptionEnum baseExceptionEnum, Throwable cause) {
        super(baseExceptionEnum, cause);
    }
}
