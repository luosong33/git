package com.jumore.jmbi.common.util;

public class Consts {

	public static final String Session_User_Key = "user_session_key";

	public static final String Session_Menu_Of_Role_Key = "session_menu_of_role_key";

	/******************* 物流后台项目缓存 start **************************/
	// 长期缓存
	public static final Integer CACHE_OVER = Integer.MAX_VALUE;
	// 临时缓存：1小时
	public static final Integer CACHE_ONE_HOUR = 60 * 60;
	/** 数据字典缓存key */
	// 数据字典缓存前缀
	public static final String DATADICTIONARY = "datadictionary:";
	// 数据字典-战略合作伙伴下次刷新时间
	public static final String STRATEGIC_NEXT_CYCLE = DATADICTIONARY
			+ "strategicNextCycle";
	/******************* 物流后台项目缓存 end **************************/

	/**
	 * 默认的语言编码
	 */
	public static final String DEFAULT_LANGUAGE_CODE = "113";

	/**
	 * 错误返回结果code
	 */
	public static final int result_Code_Error = 0;

	/**
	 * 错误返回结果msg
	 */
	public static final String result_Msg_Error = "操作失败";

	/**
	 * 正确返回结果code
	 */
	public static final int result_Code_Right = 200;

	/**
	 * 正确返回结果msg
	 */
	public static final String result_Msg_Right = "操作成功";

	/**
	 * 返回结果code的key
	 */
	public static final String RESULT_CODE = "statusCode";

	/**
	 * 返回结果msg的key
	 */
	public static final String RESULT_MSG = "message";

	/** 合同模板表名 */
	public static final String LG_CONTRACT_TPL = "LG_CONTRACT_TPL";

	/** SOP模板表名 */
	public static final String LG_SOP = "LG_SOP";

	/** ID列名 */
	public static final String ID_FIELD = "ID";

	/** areaCode，代表中国 */
	public static final String CN_AREA_CODE = "86";

	/** 公司暂停交易时间到期后自动解封定时任务名称 */
	public static final String SCORE_RANK_PARTNER_SCHEDULE = "scoreRankPartnerSchedule";

	/** 吨 */
	public static final String TON = "吨";

	/** 立方 */
	public static final String CUBE = "立方";

	/** 整数位长度 */
	public static final int LENGTH = 8;

	/**
	 * 用户session
	 */
	public static String SESSION_USER = "user";

	/**
	 * 静态文件全域名session key
	 */
	public static String STATIC_DOMAIN_URL = "staticDomainUrl";

}
