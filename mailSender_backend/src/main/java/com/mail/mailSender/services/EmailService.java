package com.mail.mailSender.services;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.mail.mailSender.DTO.Message;

public interface EmailService {

// send email to single person
	void sendEmail(String to, String subject, String msg);

//	send email to single person	
	void sendEmail(String []to, String subject, String msg);

//  send email with HTML
	void sendEmailWithHtml(String to, String subject, String htmlContent);
	
//  send email with file	
	void sendEmailWithFile(String to, String subject, String msg, File file);
	
//  send email with fileStream
	void sendEmailWithFile(String to, String subject, String msg, InputStream is);
	
	List<Message> getInboxMessages();
	
}
