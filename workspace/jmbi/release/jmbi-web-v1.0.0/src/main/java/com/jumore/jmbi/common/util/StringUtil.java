package com.jumore.jmbi.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * 字符串工具类
 * @author zw
 */
public class StringUtil {

    /**  获取随机数的数组*/
    private final static String[] RAND_STR = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

    /**
     * 判断字符窜是否为空
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0 || "null".equalsIgnoreCase(str));
    }

    /**
     * 判断字符窜是否为空
     * 
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return (str != null && str.trim().length() > 0 && !"null".equalsIgnoreCase(str));
    }

    /**
     * 将对象转换为整数
     */
    public static int strToInt(Object o) {
        String str = valueOf(o);
        return strToInt(str);
    }

    /**
     * 将字符窜转换为整数,如果转换过程中发生异常,则返回最小整数值
     * 
     * @param str
     * @return 如果转换过程中发生异常,则返回最小整数值
     */
    public static int strToInt(String str) {
        int i = Integer.MIN_VALUE;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            i = Integer.MIN_VALUE;
        }

        return i;
    }

    /**
     * 判断字符串是否为数字组成
     * 2011.01.12 by sunyouhua
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 从页面获取字符串转码
     * 2011.03.12 by songxianyou
     * @param str
     * @return
     */
    public static String transcoding(String str, CHART_ENCODEING_TYPE codType) {
        if (str == null)
            return "";
        String changedStr = "";
        try {
            if (codType == null)
                changedStr = URLDecoder.decode(str, CHART_ENCODEING_TYPE.UTF_8.code());
            else
                changedStr = URLDecoder.decode(str, codType.code());
        } catch (UnsupportedEncodingException e1) {
            return str.trim();
        }
        return changedStr == null ? "" : changedStr.trim();
    }

    /**
     * 编码类型
     * @author W  2011-3-17
     */
    public enum CHART_ENCODEING_TYPE {

        UTF_8("utf-8");

        private String codeType;

        CHART_ENCODEING_TYPE(String code) {
            this.codeType = code;
        }

        public String code() {
            return codeType;
        }
    }

    /**
     * 获取8位随机数
     * @return
     */
    public static String getRandomNum() {
        String s = "";
        for (int i = 0; i < 8; i++) {
            int a = (int) (Math.random() * 62);
            s += RAND_STR[a];
        }
        return s;
    }

    /**
     * 获取6位随机数,只有数字
     * @return
     */
    public static String getRandomNum2() {
        String s = "";
        for (int i = 0; i < 6; i++) {
            int a = (int) (Math.random() * 10);
            s += RAND_STR[a];
        }
        return s;
    }

    /**

     * 获取用户IP

     */
    public static String getRequestIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;

    }

    public static Integer parseInteger(String objValue) {
        if (objValue == null)
            return null;
        try {
            return Integer.parseInt(objValue.toString());
        } catch (Exception e) {
        }
        return null;
    }

    public static Integer parseInt(String objValue) {
        if (objValue == null)
            return null;
        try {
            return Integer.parseInt(objValue.toString());
        } catch (Exception e) {
        }
        return null;
    }

    public static Long parseLong(String objValue) {
        if (objValue == null)
            return null;
        try {
            return Long.parseLong(objValue.toString());
        } catch (Exception e) {
        }
        return null;
    }

    public static Double parseDouble(String objValue) {
        if (objValue == null)
            return null;
        try {
            return Double.parseDouble(objValue.toString());
        } catch (Exception e) {
        }
        return null;
    }

    public static Float parseFloat(String objValue) {
        if (objValue == null)
            return null;
        try {
            return Float.parseFloat(objValue.toString());
        } catch (Exception e) {
        }
        return null;
    }

    public static boolean isNull(String str) {
        return str == null || "".equals(str.trim()) ? true : false;
    }

    public static boolean isNotNull(String str) {
        return str == null || "".equals(str.trim()) ? false : true;
    }

    // 把对象转换成String
    public static String valueOf(Object obj) {
        return (obj == null || "".equals(obj) || "null".equals(obj)) ? "" : obj.toString().trim();
    }

    /**
      * 自动补对应的 字符位
      * @param isLeft    是否从左边补位
      * @param fillChar  自动填补的字符
      * @param length    填补后的总字符串长度
      * @param originalValue 原字符串 
      * @return
      */
    public static String fillString(boolean isLeft, char fillChar, int length, String originalValue) {
        if (null == originalValue || "".equals(originalValue))
            return "";

        int originalLength = originalValue.length();
        if (length <= originalLength)
            return originalValue;

        String fillCharStr = "";
        for (int op = 1; op <= length - originalLength; op++) {
            fillCharStr = fillChar + fillCharStr;
        }
        return isLeft ? (fillCharStr + originalValue) : (originalValue + fillCharStr);
    }

    /**
     * 去除填充字符
     * @param isLeft
     * @param fillChar
     * @param originalValue
     * @return
     */
    public static String removeFillChar(boolean isLeft, char fillChar, String originalValue) {
        if (null == originalValue || "".equals(originalValue))
            return "";
        int fillLength = 0;
        int originalLength = originalValue.length();

        if (isLeft) {
            for (int op = 0; op < originalLength; op++) {
                if (originalValue.substring(op, op + 1).equals(String.valueOf(fillChar)))
                    fillLength++;
                else
                    break;
            }
            return originalValue.substring(fillLength);
        } else {
            for (int op = originalLength; op > 0; op--) {
                if (originalValue.substring(op - 1, op).equals(String.valueOf(fillChar)))
                    fillLength++;
                else
                    break;
            }
            return originalValue.substring(0, originalLength - fillLength);
        }
    }

    /**
     * 字符数字转化为int型数字
     * @param value
     * @return
     */
    public static int getInt(String value) {
        int i = 0;
        try {
            if (value != null)
                value = value.trim();
            i = Integer.parseInt(value);
        } catch (Exception e) {
        }
        return i;
    }

    /**
     * 转码 js encodeURIComponent 编码后数据
     * yzh
     * 2015-03-27
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decode(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return new String(str.getBytes("ISO8859-1"), "UTF-8");
    }

    /**
     * 扩展并右对齐字符串，用指定字符填充左边。
     * <pre>
     * StringUtil.alignRight(null, *, *)     = null
     * StringUtil.alignRight("", 3, 'z')     = "zzz"
     * StringUtil.alignRight("bat", 3, 'z')  = "bat"
     * StringUtil.alignRight("bat", 5, 'z')  = "zzbat"
     * StringUtil.alignRight("bat", 1, 'z')  = "bat"
     * StringUtil.alignRight("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str 要对齐的字符串
     * @param size 扩展字符串到指定宽度
     * @param padChar 填充字符
     *
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String alignRight(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }

        int pads = size - str.length();

        if (pads <= 0) {
            return str;
        }

        return alignRight(str, size, String.valueOf(padChar));
    }

    /**
     * 扩展并右对齐字符串，用指定字符串填充左边。
     * <pre>
     * StringUtil.alignRight(null, *, *)      = null
     * StringUtil.alignRight("", 3, "z")      = "zzz"
     * StringUtil.alignRight("bat", 3, "yz")  = "bat"
     * StringUtil.alignRight("bat", 5, "yz")  = "yzbat"
     * StringUtil.alignRight("bat", 8, "yz")  = "yzyzybat"
     * StringUtil.alignRight("bat", 1, "yz")  = "bat"
     * StringUtil.alignRight("bat", -1, "yz") = "bat"
     * StringUtil.alignRight("bat", 5, null)  = "  bat"
     * StringUtil.alignRight("bat", 5, "")    = "  bat"
     * </pre>
     *
     * @param str 要对齐的字符串
     * @param size 扩展字符串到指定宽度
     * @param padStr 填充字符串
     *
     * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String alignRight(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }

        if ((padStr == null) || (padStr.length() == 0)) {
            padStr = " ";
        }

        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;

        if (pads <= 0) {
            return str;
        }

        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();

            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }

            return new String(padding).concat(str);
        }
    }

    /**
     * 物性表中文名称 # 号替换成 /
     * @param name
     * @return
     */
    public static String formatCateNameCn(String name) {
        if (StringUtil.isEmpty(name)) {
            return "";
        }
        return name.replace("#", "/");
    }

    /**
     * 生成有遮挡的公司名称
     * @param companyName
     * @return
     */
    public static String getHideCompanyName(String companyName) {
        String sign = "******";
        if (companyName == null || "".equals(companyName.trim())) {
            return "";
        }
        if (companyName.length() <= 2) {
            return companyName.substring(0, 1) + sign;
        } else if (companyName.length() == 3) {
            return companyName.substring(0, 2) + sign;
        } else if (companyName.length() > 3) {
            return companyName.substring(0, 2) + sign + companyName.substring(companyName.length() - 2);
        }

        return "";
    }

    /**
     * 取出百分号
     * @param str
     * @return
     */
    public static String filterPercent(String str) {
        if (str == null) {
            return null;
        }

        return str.replaceAll("%", "");

    }

    public static int getChineseStrLength(String s) {
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255)
                length++;
            else
                length += 2;
        }
        return length;
    }

}
