package org.jumao.bi.utis;

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
        return StringUtils.joinStr(" ", stes[3].getMethodName());
    }


    public static String getAllStackTraceFromExp(Exception ex) {
        StackTraceElement[] stes = ex.getStackTrace();
        StringBuilder sb = new StringBuilder();
        sb.append(ex.getMessage()).append(NEW_LINE);
        for (StackTraceElement ste : stes) {
            sb.append("    at ").append(ste.toString()).append(NEW_LINE);
        }
        return sb.toString();
    }


}
