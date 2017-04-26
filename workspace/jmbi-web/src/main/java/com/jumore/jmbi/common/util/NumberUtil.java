package com.jumore.jmbi.common.util;

/**
 * 数字类型常用操作
 *
 * @author: zhuwei
 * @since: 2016年9月1日  上午11:27:56
 * @history:
 */
public class NumberUtil {

    public static int IntegerNullToZero(Integer param) {

        if (param == null) {
            return 0;
        }
        return param.intValue();
    }

    public static long IntegerToLong(Integer param) {

        if (param == null) {
            return 0;
        }

        return param.longValue();
    }

}
