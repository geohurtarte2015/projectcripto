package com.renovaus.controller;

import java.util.Properties;

import javax.mail.Session;

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
	        mailSender.setPort(587);
	        mailSender.setUsername("coincaexinfo@gmail.com");
	        mailSender.setPassword("#Prueba2020");
	        
	        
	        
	       // mailSender.setHost("mail.coincaex.com");
	      //  mailSender.setPort(26);
	        	       
	       // mailSender.setUsername("administrator@coincaex.com");
	      //  mailSender.setPassword("#Admin2020#");
	        
	        
	        Properties props = mailSender.getJavaMailProperties();
	     // Get the default Session object.
	    	
	        props.put("mail.smtp.host", "localhost");
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.ssl.enable", "false");
	        props.put("mail.debug", "true");
	        props.put("mail.smtp.socketFactory.class", 
	                "javax.net.ssl.SSLSocketFactory");
	    
	        
	        return mailSender;
	    }
	    
	    @Bean
	    public SimpleMailMessage templateSimpleMessage() {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setText("This is the test email template for your email:\n%s\n");
	        return message;
	    }
	    
	 
	
	
	

}
