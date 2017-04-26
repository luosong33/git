package org.jumao.bi.utis;

import org.apache.commons.lang.StringUtils;

public class DesensitizationUtils {

	/**
	 * 获取脱敏名称
	 * 
	 * @param sourceName
	 * @return
	 */
	public static String getDesStr(String sourceName) {

		if (StringUtils.isBlank(sourceName)) {
			return "";
		}
		int strLength = sourceName.length();
		if (strLength > 5) {
			return sourceName.substring(0, 2) + "***"
					+ sourceName.substring(strLength - 2, strLength);
		} else if (strLength <= 5 && strLength > 2) {
			return sourceName.substring(0, 2) + "***";
		} else {
			return "***";
		}

	}

	public static void main(String[] args) {
		System.out.println(DesensitizationUtils.getDesStr("湖南恒博新材料有限公司"));
		System.out.println(DesensitizationUtils.getDesStr("云南铜业"));
		System.out.println(DesensitizationUtils.getDesStr("淘宝"));
		System.out.println(DesensitizationUtils.getDesStr("天地合"));
	}

}
