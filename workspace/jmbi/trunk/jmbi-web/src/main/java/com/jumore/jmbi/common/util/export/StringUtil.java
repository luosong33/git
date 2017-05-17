package com.jumore.jmbi.common.util.export;
 

public final class StringUtil {
  public static final String EMPTY = "";

  public static boolean isEmpty(String str) {
    return (str == null) || (str.isEmpty());
  }

  public static boolean isNotEmpty(String str) {
    return (str != null) && (!str.isEmpty());
  }

  public static boolean isTrimBlank(String str) {
    return (str == null) || (str.trim().isEmpty());
  }

  public static boolean isNoTrimBlank(String str) {
    return (str != null) && (!str.trim().isEmpty());
  }

  public static String capUpperCase(String str) {
    if (isEmpty(str)) {
      return str;
    }
    return str.substring(0, 1).toUpperCase().concat(str.substring(1));
  }

  public static String capLowerCase(String str) {
    if (isEmpty(str)) {
      return str;
    }
    return str.substring(0, 1).toLowerCase().concat(str.substring(1));
  }

  public static String getString(Object obj, String defaultStr) {
    if (obj != null) {
      return obj.toString();
    }
    return defaultStr;
  }

  public static String getString(Object obj) {
    if (obj != null) {
      return obj.toString();
    }
    return "";
  }
}
