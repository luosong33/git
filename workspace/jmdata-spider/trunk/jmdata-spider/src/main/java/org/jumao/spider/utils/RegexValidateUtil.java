package org.jumao.spider.utils;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexValidateUtil {  
	     
	/**
	*正则表达式：验证用户名
	*/
	public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";
		     
	/**
	*正则表达式：验证密码
	*/
	public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";
		     
	/**
	*正则表达式：验证手机号
	*/
	public static final String REGEX_MOBILE = "^((13\\d{9}$)|(15[0,1,2,3,5,6,7,8,9]\\d{8}$)|(18[0,2,5,6,7,8,9]\\d{8}$)|(147\\d{8})$)";
	//"^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	 /** 
	  * 区号+座机号码+分机号码 
	  * @param fixedPhone 
	  * @return 
	  */ 
	public static final String REGEX_FIXPHONE = "(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|" +  
    "(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";
	
	/**
	*正则表达式：验证邮箱
	*/
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		     
	/**
	*正则表达式：验证汉字
	*/
	public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
		     
	/**
	*正则表达式：验证身份证
	*/
	public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
		     
	/**
	*正则表达式：验证URL
	*/
	public static final String REGEX_URL = "^('http(s)'|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";
	//"http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
		     
	/**
	*正则表达式：验证IP地址
	*/
	public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
		     
	
    /**  
     * 匹配中国邮政编码  
     */   
	public static final String REGEX_MAIL_CODE = "[1-9]\\d{5}"; 
 
	/**
	*校验用户名
	*
	*@param username
	*@return 校验通过返回true，否则返回false
	*/
	public static boolean isUsername(String username) {
		return Pattern.matches(REGEX_USERNAME, username);
	}
		     
	/**
	*校验密码
	*
	*@param password
	*@return 校验通过返回true，否则返回false
	*/
	public static boolean isPassword(String password) {
		return Pattern.matches(REGEX_PASSWORD, password);
	}
		     
	 /**
	   * 验证手机号码
	   * @param mobiles
	   * @return
	   */
	  public static boolean checkMobileNumber(String mobileNumber){
	    boolean flag = false;
	    try{
	        Pattern regex = Pattern.compile(REGEX_MOBILE);
	        Matcher matcher = regex.matcher(mobileNumber);
	        flag = matcher.matches();
	      }catch(Exception e){
	        flag = false;
	      }
	    return flag;
	  }
		     
	  /* 验证邮箱
	   * @param email
	   * @return
	   */
	  public static boolean checkEmail(String email){
	    boolean flag = false;
	    try{
	        Pattern regex = Pattern.compile(REGEX_EMAIL);
	        Matcher matcher = regex.matcher(email);
	        flag = matcher.matches();
	      }catch(Exception e){
	        flag = false;
	      }
	    return flag;
	  }
	/**
	*校验汉字
	*
	*@param chinese
	*@return 校验通过返回true，否则返回false
	*/
	public static boolean isChinese(String chinese) {
		return Pattern.matches(REGEX_CHINESE, chinese);
	}
		     
	/**
	*校验身份证
	*
	*@param idCard
	*@return 校验通过返回true，否则返回false
	*/
	public static boolean isIDCard(String idCard) {
		return Pattern.matches(REGEX_ID_CARD, idCard);
	}
		     
	/**
	*校验URL
	*
	*@param url
	*@return 校验通过返回true，否则返回false
	*/
	public static boolean isUrl(String url) {
	   return Pattern.matches(REGEX_URL, url);
	}
		     
	/**
	*校验IP地址
	*
	*@param ipAddr
	*@return
	*/
	public static boolean isIPAddr(String ipAddr) {
		return Pattern.matches(REGEX_IP_ADDR, ipAddr);
	}
		     
		        
	/**
	*url是否规则
	*@param potostr
	*@param urlstr
	*@return
	*/
	public static HashMap urlre(String potostr, String urlstr) {  
		String hrefstr ="";
		if(urlstr.startsWith("//")==true)
		    hrefstr = potostr+":"+urlstr;
		else if(urlstr.startsWith(potostr)==true)
			hrefstr = urlstr;
		else
			hrefstr = "";
		HashMap hm =null;
		Pattern p = Pattern.compile(REGEX_URL,Pattern.CASE_INSENSITIVE );
		Matcher m = p.matcher(hrefstr);    
			          
		if(m.find()){
			 hm =new HashMap();
			 hm.put("url", m.group()); 
			 hm.put("urlbool", true); 
			 return hm;
		} else
		   return  hm; 
	}
			    
	/**
	* 手机号:目前全国有27种手机号段。
	* 移动有16个号段：134、135、136、137、138、139、147、150、151、152、157、158、159、182、187、188。其中147、157、188是3G号段，其他都是2G号段。
	* 联通有7种号段：130、131、132、155、156、185、186。其中186是3G（WCDMA）号段，其余为2G号段。
	* 电信有4个号段：133、153、180、189。其中189是3G号段（CDMA2000），133号段主要用作无线网卡号。
	* 150、151、152、153、155、156、157、158、159 九个;
	* 130、131、132、133、134、135、136、137、138、139 十个;
	* 180、182、185、186、187、188、189 七个;
	* 13、15、18三个号段共30个号段，154、181、183、184暂时没有，加上147共27个。
	*/
	 private boolean telCheck(String tel){
		Pattern p = Pattern.compile(REGEX_MOBILE);
		Matcher m = p.matcher(tel);
		return m.matches();
	}

	 /** 
	  * 区号+座机号码+分机号码 
	  * @param fixedPhone 
	  * @return 
	  */  
	 public static boolean isFixedPhone(String fixedPhone){  
	       return Pattern.matches(REGEX_FIXPHONE, fixedPhone);  
	  }  
	    /**  
	     * 匹配中国邮政编码  
	     * @param postcode 邮政编码  
	     * @return 验证成功返回true，验证失败返回false  
	     */   
	    public static boolean isPostCode(String postCode){  
	        return Pattern.matches(REGEX_MAIL_CODE, postCode);  
	    } 
	    
	public static void main(String[] args) {
		String username = "fdsdfsdj";
		System.out.println(RegexValidateUtil.isUsername(username));
		System.out.println(RegexValidateUtil.isChinese(username));
	}
}
