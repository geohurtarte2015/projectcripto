package com.renovaus.controller;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;



	@Configuration
	@ComponentScan(basePackages = { "com.renovaus.controller" })
	public class EmailConfiguration {
	    
	    @Bean
	    public JavaMailSender getJavaMailSender() {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(25);
	        
	       
	        mailSender.setUsername("admin@coincaex.com");
	        mailSender.setPassword("#Admin2020");
	        
	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.debug", "true");
	        
	        return mailSender;
	    }
	    
	    @Bean
	    public SimpleMailMessage templateSimpleMessage() {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setText("This is the test email template for your email:\n%s\n");
	        return message;
	    }
	    
	 
	
	
	

}
