package com.renovaus.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


public class ConfigProperties {



	private String urlWebservice; 
	
	public ConfigProperties() {
		this.urlWebservice="https://gt.coincaex.com/sendmailphp/pruebapost";
	}	

	public String getUrlWebservice() {	
		return urlWebservice;
	}


}
