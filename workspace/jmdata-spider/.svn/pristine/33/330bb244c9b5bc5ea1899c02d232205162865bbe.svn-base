package org.jumao.mails.mainmail;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jumao.mails.beans.MailBean;
import org.jumao.mails.service.impl.MailSenderServiceImpl;

public class MailMessage {

    @Resource
    public MailSenderServiceImpl mailSenderServiceImpl;
    @Resource
    public MailBean              mailBean;
    private static Logger        logger = Logger.getLogger(MailMessage.class);

    public void sendEmail(String title, Exception ex) {
        try {

            // 创建邮件
            mailBean.setSubject("warning: " + title + " " + new Date() + " on" + getHostName());
            // 打印出堆栈信息
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            StringBuffer buffer = sw.getBuffer();
            logger.error(buffer.toString());
            // mailBean.setTemplate("hello ${userName} !\t\n"+ex.getMessage() +
            // "\n\t" +joinPoint.toLongString());
            mailBean.setTemplate("hello admin !\t\n" + buffer.toString() + "\n\t");
            // 发送邮件
            mailSenderServiceImpl.send(mailBean);
            sw.close();
        } catch (Exception e) {

            logger.error(e.getMessage());
            e.printStackTrace();
        }

    }

    public void sendEmail(String title, String message) {
        try {

            // 创建邮件
            mailBean.setSubject("warning: " + title + " " + new Date());
            mailBean.setTemplate("hello admin !\t\n" + message + "\n\t");
            // 发送邮件
            mailSenderServiceImpl.send(mailBean);

        } catch (Exception e) {

            logger.error(e.getMessage());
            e.printStackTrace();
        }

    }

    private String getHostName() {
        try {

            return "Host:" + InetAddress.getLocalHost().getHostName() + " IP:" + InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException uhe) {
            return "UNKNOWN";
        }
    }

}
