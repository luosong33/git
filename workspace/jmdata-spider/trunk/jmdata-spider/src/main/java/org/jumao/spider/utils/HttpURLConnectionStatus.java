package org.jumao.spider.utils;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionStatus {

	public static int tryConnectionUrl(String address) throws Exception {
		  int status = 404;
		  try {
			  boolean istrue = RegexValidateUtil.isUrl(address);
			  if(istrue==true){
				  URL urlObj = new URL(address);
				  HttpURLConnection oc = (HttpURLConnection) urlObj.openConnection();
				  oc.setUseCaches(false);
				  oc.setConnectTimeout(3000); // 设置超时时间
				  status = oc.getResponseCode();// 请求状态
				  if (200 == status) {
				   // 200是请求地址顺利连通。
				   return status;
				  }
			  } else {
				  throw new RuntimeException("  >> 抛出异常 : 无效的url \n");
			  }
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return status;
		 }
	
}
