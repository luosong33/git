package org.jumao.bi.utis;

import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.exceptions.WrapEx;

public class GeneralUtils {

    public static String NEW_LINE = "\n";

    /**
     * @return " " + method name
     */
    public static String getCurrMethod() {
        StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        return StringUtils.joinStr(" ", stes[2].getMethodName());
    }

    public static String getCurrMethod(String prefix) {
        return StringUtils.joinStr(prefix, getCurrMethod());
    }


    /**
     * @return " " + method name
     */
    public static String getUpperMethod() {
        StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        return StringUtils.joinStr(" ", stes[Key.Num3].getMethodName());
    }

    /**
     * @param ex
     * @return
     */
    public static String getAllStackTraceFromExp(Throwable ex) {
        StackTraceElement[] stes = ex.getStackTrace();
        StringBuilder sb = new StringBuilder();
        sb.append(ex.getMessage()).append(NEW_LINE);
        for (StackTraceElement ste : stes) {
            sb.append("    at ").append(ste.toString()).append(NEW_LINE);
        }
        return sb.toString();
    }


    /**
     * 使 toString 方法能打印完整的错误栈
     */
    public static Exception getWrapEx(Exception e) {
        WrapEx wrapEx = new WrapEx(getAllStackTraceSb(e));
        wrapEx.setStackTrace(e.getStackTrace());
        return wrapEx;
    }

    public static StringBuilder getAllStackTraceSb(Throwable ex) {
        StackTraceElement[] stes = ex.getStackTrace();
        StringBuilder sb = new StringBuilder();
        sb.append(ex.getMessage());

        for (StackTraceElement ste : stes) {
            sb.append(" ").append(ste.toString());
//            sb.append("    at ").append(ste.toString()).append(NEW_LINE);
        }
        return sb;
    }


}
