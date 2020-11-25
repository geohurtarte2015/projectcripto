package com.renovaus;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.renovaus.configuration.ConfigProperties;
import com.renovaus.controller.EmailService;
import com.renovaus.controller.EmailServiceImpl;
import com.renovaus.controller.EmailToWebservice;
import com.renovaus.controller.SendMail;
import com.renovaus.model.admin.IAdmin;
import com.renovaus.model.orden.IOrden;
import com.renovaus.model.rol.IRol;
import com.renovaus.model.user.IUser;
import com.renovaus.pojo.Admin;
import com.renovaus.pojo.Estado;
import com.renovaus.pojo.User;



@SpringBootApplication
public class ProjectcriptoApplication extends SpringBootServletInitializer implements CommandLineRunner {
	
    private static final Logger log = LoggerFactory.getLogger(ProjectcriptoApplication.class);
    
    private EmailToWebservice emailToWebservice = new EmailToWebservice();
  
    @Autowired
    public EmailService emailService;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	 
	@Autowired
	private IRol iRol;
	
	@Autowired
	private IUser iUser;
	
	@Autowired
	private IAdmin iAdmin;
	
	@Autowired
	private IOrden iOrden;

	public static void main(String[] args) {
		SpringApplication.run(ProjectcriptoApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		


	
		
	log.info("[ENVIANDO EMAIL PRUEBA]");		
		System.out.println("Sending Email...");	
		//emailService.sendSimpleMessage("edgar.hurtarte@gmail.com", "Bienvenido a Coincaex", "Transforma tus finanzas, he invierte en criptomonodeas.");
		emailToWebservice.sendToPhp("edgar.hurtarte@gmail.com","Solicitud enviada a Coincaex ","Esta es una prueba, email enviado desde PHP");
		log.info("[TERMINADO  EMAIL]");
		
    
		
		//String msg= "CARGANDO LISTA DE ";
		//String msgsave = "GUARDANDO....";

		//SendMail sendMail = new SendMail();
		//sendMail.sendSimpleText();
		
//		log.info("[CARGANDO LISTA DE ROLES]");
//        List<Rol> rols = Arrays.asList(
//        		new Rol("Normal"),
//        		new Rol("Vendedor2")
//        );
		
//	    log.info("[GUARDANDO.....]");
//	    rols.forEach(rol -> {
//            log.info("Saving...{}", rol.getDescription());
//            iRol.save(rol);
//        });
//	    
//	    
        
		/*
		 * log.info("[CARGANDO LISTA DE USUARIOS]"); BigInteger value = new
		 * BigInteger("1997989900101"); List<User> users = Arrays.asList( new
		 * User("Jose","Fernandez","Masculino",57856308,"jfernandez","#123",new
		 * Estado(1),"39617726",value,null,null,null,new Admin(1),"jfernadez@gmail.com")
		 * 
		 * );
		 * 
		 * 
		 * log.info("[GUARDANDO USUARIOS.....]"); users.forEach(user -> {
		 * log.info("Saving...{}", user.getNombre()); iUser.save(user); });
		 */
        
//        log.info(msg+"ADMINS");
//        List<Admin> admins = Arrays.asList(
//         new Admin("Juan","Carlos","Masculino",52352347,"jcarlos","#44555",new Estado(1),null,new Rol(1),"josex@gmail.com")	
//        );
//        
//        log.info("[GUARDANDO ADMINS.....]");
//        admins.forEach(admin -> {
//            log.info("Saving...{}", admin.getNombre());
//            iAdmin.save(admin);
//        });
//		
		
//		log.info(msg+"ORDENES");
//		BigDecimal valFiat = new BigDecimal(1250.30);
//		BigDecimal valCripto = new BigDecimal(0.0000000065);
//		
//		List<Orden> ordenes = Arrays.asList(				
//			new Orden(new TipoPago(1),new TipoNegocio(1),null,valFiat,valCripto,new User(2),new EstadoOrden(1))
//		 );
//		
//		log.info(msgsave+"ORDENES");
//		ordenes.forEach(orden->{
//			log.info("Saving...{}",orden.getId());
//			iOrden.save(orden);
//		});
//		

        // find all
       //log.info("[FIND_ALL] {}", iUser.findAll());
       
       
	
		
		// find all
		// log.info("[FIND_ALL] {}", iUser.findAllTable());
       
       
        
		// find all
		// log.info("[FIND_ALL] {}", iOrden.findAll());
        
		// find all
		//log.info("[FIND_ALL] {}", iOrden.findByUser(3));
    
		//log.info("[FIND_ALL] {}", iUser.findByUser(1));
		
		
		
		
		
	    log.info("[FINALIZADO]");
	    log.info("[EXIT]");
		
	}

	
}

