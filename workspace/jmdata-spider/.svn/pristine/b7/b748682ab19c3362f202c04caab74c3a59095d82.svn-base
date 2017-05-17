package org.jumao.mails.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.jumao.mails.beans.MailBean;

public interface IMailSenderService {
	
	public MimeMessage createMimeMessage(MailBean mailBean) throws MessagingException, UnsupportedEncodingException;
	
	public void sendMail(MailBean mailBean) throws UnsupportedEncodingException, MessagingException;

	public boolean send(MailBean mailBean) throws MessagingException,IOException;
	
}