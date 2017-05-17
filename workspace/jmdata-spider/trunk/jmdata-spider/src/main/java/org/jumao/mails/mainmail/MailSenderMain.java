package org.jumao.mails.mainmail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.jumao.mails.beans.MailBean;
import org.jumao.mails.service.IMailSenderService;
import org.jumao.mails.service.impl.MailSenderServiceImpl;
import org.springframework.context.ApplicationContext;  
import org.springframework.context.support.ClassPathXmlApplicationContext;  
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
  
public class MailSenderMain {
	@Resource
	private static IMailSenderService mailserver;
	@Resource
	static MailSender sender;
	@Resource
	static SimpleMailMessage mailMessage;
	
    /**  
     * @throws Exception  
     */  
    public static void mailSenderMain(String fromstr, String fromNamestr, String subjects, String []toEmails, String context) throws Exception{  
    	
        MailBean mailBean = new MailBean();
        mailBean.setFrom(fromstr);
        mailBean.setFromName(fromNamestr);
        mailBean.setSubject(subjects);
        mailBean.setToEmails(toEmails);//new String[]{"sdfasd@qq.com","asfasd@139.com"});
        mailBean.setContext(context);//"<a href='www.baidu.com'><font color='red'>fdsfdsf</font></a>");
        mailserver.sendMail(mailBean);
    }
    
	public void testSendMail() throws MessagingException,IOException{
		
		//创建邮件
		MailBean mailBean = new MailBean();
		mailBean.setFrom("wzqdemo@163.com");
		mailBean.setSubject("hello world");
		mailBean.setToEmails(new String[]{"wzqdemo@gmail.com"});
		mailBean.setTemplate("hello ${userName} !<a href='www.baidu.com' >baidu</a>");
		Map map = new HashMap();
		map.put("userName", "Haley Wang");
		mailBean.setData(map);
		
		//发送邮件
		mailserver.send(mailBean);
		
	}
	
}  