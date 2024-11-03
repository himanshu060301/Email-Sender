package com.mail.mailSender.Implementation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mail.mailSender.DTO.Message;
import com.mail.mailSender.services.EmailService;

import jakarta.mail.Folder;
import jakarta.mail.MessagingException;
import jakarta.mail.NoSuchProviderException;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Value("${mail.store.protocol}")
	String protocol;
	
	@Value("${mail.imaps.host}")
	String host;
	
	@Value("${mail.imaps.port}")
	String port;
	
	@Value("${spring.mail.username}")
	String username;
	
	@Value("${spring.mail.password}")
	String password;
	
	//@Autowired
	private JavaMailSender mailSender;
	
	private Logger logger=LoggerFactory.getLogger(EmailServiceImpl.class);
	
	
	
	public EmailServiceImpl(JavaMailSender mailSender) { 
		 this.mailSender =mailSender; 
	}
	

	@Override
	public void sendEmail(String to, String subject, String msg) {
		try {
	        SimpleMailMessage simpleMsg = new SimpleMailMessage();
	        simpleMsg.setTo(to);
	        simpleMsg.setSubject(subject);
	        simpleMsg.setText(msg);
	        simpleMsg.setFrom("Email_Address");
	        mailSender.send(simpleMsg);
	        logger.info("Email has been sent...");
	    } catch (Exception e) {
	        logger.error("Failed to send email", e);
	        throw e;
	    }
	}

	@Override
	public void sendEmail(String[] to, String subject, String msg) {
		try {
	        SimpleMailMessage simpleMsg = new SimpleMailMessage();
	        simpleMsg.setTo(to);
	        simpleMsg.setSubject(subject);
	        simpleMsg.setText(msg);
	        simpleMsg.setFrom("Email_Address");
	        mailSender.send(simpleMsg);
	        logger.info("Email has been sent...");
	    } catch (Exception e) {
	        logger.error("Failed to send email", e);
	        throw e;
	    }
		
	}

	@Override
	public void sendEmailWithHtml(String to, String subject, String htmlContent) {
		MimeMessage mailMsg=mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper=new MimeMessageHelper(mailMsg,true,"UTF-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom("Email_Address");
			helper.setText(htmlContent,true);
			mailSender.send(mailMsg);
	        logger.info("Email has been sent...");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void sendEmailWithFile(String to, String subject, String msg, File file) {
		MimeMessage mailMsg=mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper=new MimeMessageHelper(mailMsg,true,"UTF-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom("Email_Address");
			helper.setText(msg);
			
			FileSystemResource fileSystemResource=new FileSystemResource(file);
			helper.addAttachment(fileSystemResource.getFilename(), file);
			mailSender.send(mailMsg);
	        logger.info("Email has been sent...");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void sendEmailWithFile(String to, String subject, String msg, InputStream is) {
		MimeMessage mailMsg=mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper=new MimeMessageHelper(mailMsg,true,"UTF-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom("Email_Address");
			helper.setText(msg,true);
			
			File file=new File("src/main/resources/email/test.png");
			Files.copy(is, file.toPath(),StandardCopyOption.REPLACE_EXISTING);
			FileSystemResource fileSystemResource=new FileSystemResource(file);
			helper.addAttachment(fileSystemResource.getFilename(), file);
			mailSender.send(mailMsg);
	        logger.info("Email has been sent...");
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<Message> getInboxMessages() {
		
		Properties configurations=new Properties();
		configurations.setProperty("mail.store.protocol", protocol);
		configurations.setProperty("mail.imaps.host", host);
		configurations.setProperty("mail.imaps.port", port);
//		configurations.setProperty("mail.imaps.ssl.enable", "true");
//		configurations.setProperty("mail.imaps.starttls.enable", "true");
		
		Session session=Session.getDefaultInstance(configurations);
		try {
			Store store=session.getStore();
			store.connect(username,password);
			
			Folder inbox=store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);
			jakarta.mail.Message[] messages=inbox.getMessages();
			
			List<Message> list=new ArrayList<>();
			
			for(jakarta.mail.Message msg:messages) {
				System.out.println(msg.getSubject());
				
				String content=getContentFromEmailMessage(msg);
				List<String> files=getFilesFromEmailMessage(msg);
				System.out.println("---------------");
				
				list.add(Message.builder().subjects(msg.getSubject()).build());
			}
			return list;
			
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}


	private List<String> getFilesFromEmailMessage(jakarta.mail.Message msg) {
		// TODO Auto-generated method stub
		return null;
	}


	private String getContentFromEmailMessage(jakarta.mail.Message msg) {
		// TODO Auto-generated method stub
		return null;
	}

}
