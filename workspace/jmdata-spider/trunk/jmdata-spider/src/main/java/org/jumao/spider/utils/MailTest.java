package org.jumao.spider.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.jumao.mails.beans.MailBean;
import org.jumao.mails.service.impl.MailSenderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;


public class MailTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context/applicationContext-spider.xml"); 
		JavaMailSenderImpl bean = (JavaMailSenderImpl)context.getBean("mailSender"); 

		
		try {
			
			int t = 100;
			int r = t/0;
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
			StringBuffer buffer = sw.getBuffer();
			MailBean mailBean = new MailBean();
			//String template = buffer.toString();
			//mailBean.setTemplate(template);
			mailBean.setFrom("huyumin@jumore.com");
			mailBean.setFromName("server");
		
			String[] toEmails= {"huyumin@jumore.com"};
			mailBean.setToEmails(toEmails);
			mailBean.setSubject("Exception test");
    		mailBean.setTemplate("hello ${userName} !\t\n"+ex.getMessage() + "\n\t" + buffer.toString() + "\n\t");
    		Map map = new HashMap();
    		map.put("userName", "Lao Wang\n\t");
    		mailBean.setData(map);
    		//发送邮件
    		MailSenderServiceImpl mailSenderServiceImpl = new MailSenderServiceImpl();
    		try {
    		
				mailSenderServiceImpl.send(mailBean);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
