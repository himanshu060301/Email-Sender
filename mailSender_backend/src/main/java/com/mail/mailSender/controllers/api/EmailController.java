package com.mail.mailSender.controllers.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mail.mailSender.DTO.CustomResponse;
import com.mail.mailSender.DTO.EmailRequest;
import com.mail.mailSender.services.EmailService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/email/")
public class EmailController {
	
	//@Autowired
	private EmailService emailService;
	
	public EmailController(EmailService emailService) {
		super();
		this.emailService = emailService;
	}


	@PostMapping("/send")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest req){
		emailService.sendEmailWithHtml(req.getTo(), req.getSubject(), req.getMsg());
		return ResponseEntity.ok(CustomResponse.builder().msg("Email send successfully !!").httpStatus(HttpStatus.OK).success(true).build());
	}
	
	@PostMapping("/send-with-file")
	public ResponseEntity<?> sendEmail(@RequestPart EmailRequest req, @RequestPart MultipartFile file) throws IOException{
		emailService.sendEmailWithFile(req.getTo(), req.getSubject(), req.getMsg(),file.getInputStream());
		return ResponseEntity.ok(CustomResponse.builder().msg("Email send successfully !!").httpStatus(HttpStatus.OK).success(true).build());
	}
}
