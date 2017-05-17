package org.jumao.aops;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jumao.mails.beans.MailBean;
import org.jumao.mails.service.impl.MailSenderServiceImpl;
import org.jumao.spider.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
  
/**
 * throwing
 * 
 */
@Aspect
@Component("spideraspcejHandler")
public class SpiderAspceJAdvice {
	
	@Resource
	public MailSenderServiceImpl mailSenderServiceImpl;
	@Resource
	public MailBean mailBean;
	
    @Value("${smsMobile}")
    private String smsMobile;
    
    @Value("${smsTplCode}")
    private String smsTplCode;
    
    @Value("${smsUrl}")
    private String smsUrl;
    
	private static Logger logger = Logger.getLogger(SpiderAspceJAdvice.class);
	
    /** 
     * Pointcut 
     * 定义Pointcut，Pointcut的名称为aspectjMethod()，此方法没有返回值和参数 
     * 该方法就是一个标识，不进行调用 
     */  
    @Pointcut("execution(* spider*(..))")
    private void aspectjMethod(){
    	
    };
      
    
    @Before("aspectjMethod()")  
    public void doBefore() throws IOException{  
    	
    }
    
    /** 
     * 核心业务逻辑调用异常退出后，执行此Advice，处理错误信息 和 邮件报警
     *  
     * @param joinPoint 
     * @param ex 
     */  
    @AfterThrowing(value = "aspectjMethod()", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
        try {
             
        	//创建邮件
    		mailBean.setSubject("Error:错误信息发生在:"+new Date());
    		//打印出堆栈信息
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
			StringBuffer buffer = sw.getBuffer();
			logger.error(buffer.toString());
    		//mailBean.setTemplate("hello ${userName} !\t\n"+ex.getMessage() + "\n\t" +joinPoint.toLongString());
			mailBean.setTemplate("hello admin !\t\n"+buffer.toString() + "\n\t" +joinPoint.toLongString());
//    		Map map = new HashMap();
//    		map.put("userName", "Lao Wang\n\t");
//    		mailBean.setData(map);
    		//发送邮件
    		mailSenderServiceImpl.send(mailBean);		
    		sw.close();
    		
    		//严重错误发送短信通知
    		JSONObject jsonParam = new JSONObject();
    		jsonParam.put("args", ex.getMessage());
    		jsonParam.put("mobile", smsMobile);
    		jsonParam.put("tplCode", smsTplCode);
    	
    		HttpUtil.post(smsUrl, jsonParam);

		} catch (Exception e) {
			
			logger.error(e.getMessage());
			e.printStackTrace();
		}
        
    }


	public String getSmsMobile() {
		return smsMobile;
	}


	public void setSmsMobile(String smsMobile) {
		this.smsMobile = smsMobile;
	}


	public String getSmsTplCode() {
		return smsTplCode;
	}


	public void setSmsTplCode(String smsTplCode) {
		this.smsTplCode = smsTplCode;
	}


	public String getSmsUrl() {
		return smsUrl;
	}


	public void setSmsUrl(String smsUrl) {
		this.smsUrl = smsUrl;
	}
    
    
}