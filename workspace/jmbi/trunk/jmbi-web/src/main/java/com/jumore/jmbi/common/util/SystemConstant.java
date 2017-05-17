/*
 *	Copyright © 2013 Changsha jumore Network Technology Co., Ltd. All rights reserved.
 *	
 *	http://www.jumore.com
 */

package com.jumore.jmbi.common.util;

import com.jumore.jmbi.common.util.properties.PropertiesUtil;

/**
 * 系统常量
 * 
 * @author Herbert
 * 
 */
public class SystemConstant {

	/**
	 * 应用部署路径的KEY
	 */
	public static String XYX_CMS_ROOT = PropertyUtils.getRoot();

	/**
	 * 上传文件夹
	 */
	public static String UPLOAD_FOLDER = "upload/photo";

	/**
	 * 备份文件夹
	 */
	public static String BACKUP_FOLDER = "/WEB-INF/backup";

	/**
	 * Session中的管理员Key
	 */
	public static final String SESSION_ADMIN = "SESSION_ADMIN";

	/**
	 * 头像URL 180x180
	 */
	public static final String FACE_URL = "http://faceurl.jumore.com/face";

	/**
	 * 
	 */
	public static final String LANGUAGE = "language";


	/**
	 * 文件上传服务地址
	 */
	public static final String FILE_SERVER_UPLOAD_URL = PropertiesUtil.getWebImagePath();

	/**
	 * 
	 */
	public static final String FILE_UPLOAD_DOMAIN = PropertiesUtil.getWebDomain();
}
