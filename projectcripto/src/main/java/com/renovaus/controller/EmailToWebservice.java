package com.renovaus.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.renovaus.configuration.ConfigProperties;

public class EmailToWebservice {
	
	private ConfigProperties configProperties= new ConfigProperties();
	
	public String sendToPhp(String email,String subject,String message){
		System.out.println("Para: "+email+"\n "+"Titulo: "+subject+"\n "+subject+"Mensaje: "+message);	
		String url = configProperties.getUrlWebservice();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("email", email);
		map.add("subject", subject);
		map.add("message", message);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
		System.out.println("Respuesta WebservicePHP: "+response.getBody()+" Status WebservicePHP: "+response.getStatusCodeValue());		
		return "";
	}
	

}
