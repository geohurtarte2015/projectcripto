package com.renovaus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SendMail {
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	
	public void sendSimpleText() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("geoyova@hotmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }
	

}
