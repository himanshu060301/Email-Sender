package com.mail.mailSender;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mail.mailSender.services.EmailService;

@SpringBootTest
public class EmailSenderTest {

	@Autowired
	private EmailService emailService;
	
	@Test
	void emailSendTest() {
		System.out.println("sending email...");
		emailService.sendEmail("hksk3542@gmail.com", "Email from Spring Boot", "Testing mail");
	}
	
	@Test
	void sendHtmlInEmail() {
		String html="<h1 style='color:red;border:1px solid red;'>Welcome Email</h1>";
		emailService.sendEmailWithHtml("hksk3542@gmail.com", "Email from Spring Boot", html);
	}
	
	@Test
	void sendEmailWithFile() {
		emailService.sendEmailWithFile("hksk3542@gmail.com", "Email with file", "This email contains file",
				new File("E:/Development/Spring_Boot/mailSender/src/main/resources/static/img/cartoon-cute-school-boy-photo.jpg"));
	}
	
	@Test
	void sendEmailWithFileWithStream() {
		File file=new File("E:/Development/Spring_Boot/mailSender/src/main/resources/static/img/cartoon-cute-school-boy-photo.jpg"); 
		
		try {
			InputStream  is = new FileInputStream(file);
			emailService.sendEmailWithFile("hksk3542@gmail.com", "Email with file stream", "This email contains file",is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void getInbox(){
		emailService.getInboxMessages();
	}
}
