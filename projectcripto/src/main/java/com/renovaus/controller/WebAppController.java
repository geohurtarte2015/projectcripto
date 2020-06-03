package com.renovaus.controller;

import org.hibernate.Hibernate;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.renovaus.ProjectcriptoApplication;
import com.renovaus.model.orden.IOrden;
import com.renovaus.model.user.IUser;
import com.renovaus.pojo.Admin;
import com.renovaus.pojo.Estado;
import com.renovaus.pojo.User;
import com.renovaus.pojo.UserLogin;
import com.renovaus.services.PasswordGenerator;
import com.renovaus.services.UserGenerator;
import com.renovaus.utils.datatable.GetJson;
import com.test.Greeting;

@Controller
public class WebAppController {

	
	  private String appMode;
	  
	  private static final Logger log = LoggerFactory.getLogger(ProjectcriptoApplication.class);
	  
	  		@Autowired
	  		private IUser iUser;
	  		
	  		@Autowired
	  		private IOrden iOrden;
	  		
	  	    @Autowired
	  	    public EmailService emailService;

		    @Autowired
		    public WebAppController(Environment environment){
		        this.appMode = environment.getProperty("app-mode");
		    }

		    @RequestMapping("/")
	    	public String index(Model model, HttpSession session){
		    @SuppressWarnings("unchecked")
		    String nombreCompleto = (String) session.getAttribute("nombre");
				
			
			if (nombreCompleto == null) {
			    nombreCompleto = new String();
			}	
			
			model.addAttribute("nombre", nombreCompleto);		
	        model.addAttribute("datetime", new Date());
	        model.addAttribute("username", "Giovanni Hurtarte");
	        model.addAttribute("mode", appMode);
	        model.addAttribute("userLogin", new UserLogin());
	        return "plantillaUser/index";
		    }
		    
		    
		    @RequestMapping("/login")
	    	public String indexLogin(Model model, HttpSession session){
		    @SuppressWarnings("unchecked")
		    String nombreCompleto = (String) session.getAttribute("nombre");
				
			
			if (nombreCompleto == null) {
			    nombreCompleto = new String();
			}	
			
			model.addAttribute("nombre", nombreCompleto);		
	        model.addAttribute("datetime", new Date());
	        model.addAttribute("username", "Giovanni Hurtarte");
	        model.addAttribute("mode", appMode);
	        model.addAttribute("userLogin", new UserLogin());
	        return "plantillaAdmin/login";
		    }
		    
		    
		    @RequestMapping("/logout")
	    	public String logout(Model model, HttpSession session){
		    @SuppressWarnings("unchecked")
		    String nombreCompleto = (String) session.getAttribute("nombre");
			if (nombreCompleto == null) {
			    nombreCompleto = new String();
			}	
			
			model.addAttribute("nombre", nombreCompleto);		

	        return "plantillaAdmin/logout";
		    }

		    
		    @RequestMapping(value = "/uploadData", method = RequestMethod.POST,produces = MediaType.TEXT_HTML_VALUE)
		    @ResponseBody
	    	public String loadData(
	    			//@RequestParam String nombre,
//	    			@RequestParam String apellido,
//	    			@RequestParam String telefono,
//	    			@RequestParam String email,
//	    			@RequestParam String nit,
//	    			@RequestParam String dpi,
	    			@RequestParam String genero,
	    			@RequestParam String password,
	    			@RequestParam("uploadData") MultipartFile uploadData,
//	    			@RequestParam("fileDpiRostro") MultipartFile fileDpiRostro,
//	    			@RequestParam("fileDpiReverso") MultipartFile fileDpiReverso,
	    			HttpServletRequest request){
		    	
		    		System.out.println("upload");
		    	
			    	String fileRostroName = StringUtils.cleanPath(uploadData.getOriginalFilename());
			    	System.out.println("Archivo.. "+fileRostroName);
			    	
			    	String fileDpiRostroName = StringUtils.cleanPath(uploadData.getOriginalFilename());
			    	System.out.println("Archivo.. "+fileDpiRostroName);
			    	
			    	String fileDpiReversoName = StringUtils.cleanPath(uploadData.getOriginalFilename());
			    	System.out.println("Archivo.. "+fileDpiReversoName);
		    		

		    		return "succesful";
		    		}
		    
		    
		    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST,produces = MediaType.TEXT_HTML_VALUE)
		    @ResponseBody
		    public String uploadFile(
		    	@RequestParam String nombre,
    			@RequestParam String apellido,
    			@RequestParam String telefono,
    			@RequestParam String email,
    			@RequestParam String nit,
    			@RequestParam String dpi,
	    		@RequestParam String genero,	    	
		        @RequestParam("rostro") MultipartFile rostro,
		        @RequestParam("dpifrontal") MultipartFile dpifrontal,
		        @RequestParam("dpireverso") MultipartFile dpireverso) 
		    	{
		    	
		      try {
		    	  
		    	  String newGenero=null;
		    	  if(genero!=null) {
		    	  String[] arrayGenero = genero.split(",");
		    	  newGenero = arrayGenero[1];
		    	  }
		    	  
		    	 System.out.println(nombre+" "+apellido+" "+telefono+" "+email+" "+nit+" "+dpi+" "
		    			           +newGenero+" "+rostro.getOriginalFilename()+" "+dpifrontal.getOriginalFilename()+
		    			           " "+dpireverso.getOriginalFilename());
		    	 
		    	 byte[] blobRostro = InputStreamConvert.readBytesFromFile(rostro);
		         byte[] blobDpiFrontal = InputStreamConvert.readBytesFromFile(dpifrontal);
		         byte[] blobDpiReverso = InputStreamConvert.readBytesFromFile(dpireverso);
		         
		        		         
		         BigInteger bigDpi = new BigInteger(dpi); 
		         iUser.save(
		        		 new User(
		        				 nombre,
		        				 apellido,
		        				 newGenero,
		        				 Integer.parseInt(telefono),
		        				 null,//user
		        				 null,//password
		        				 new Estado(2),
		        				 nit,
		        				 bigDpi,
		        				 blobRostro,
		        				 blobDpiFrontal,
		        				 blobDpiReverso,
		        				 new Admin(1),
		        				 email));
		         
		  
		      }
		      catch (Exception e) {
		        System.out.println(e.getMessage());
		        return "Error en carga de datos";
		      }
		      
		      return "Datos Cargados correctamente";
		    } 

		    
		    @RequestMapping("/register")
	    	public String register(Model model, HttpSession session){
		    @SuppressWarnings("unchecked")
		    String nombreCompleto = (String) session.getAttribute("nombre");
			if (nombreCompleto == null) {
			    nombreCompleto = new String();
			}	
			
			model.addAttribute("nombre", nombreCompleto);		

	        return "plantillaAdmin/register";
		    }
		    
		    
		    @RequestMapping("/inicio")
	    	public String admin(Model model, HttpSession session){
		    	   @SuppressWarnings("unchecked")
			
				    String nombreCompleto = (String) session.getAttribute("nombre");
				    
				    if (nombreCompleto == null) {
				    	nombreCompleto = new String();
					}	
		    	
		    	   
				    model.addAttribute("nombre", nombreCompleto);				
			        model.addAttribute("datetime", new Date());
			        model.addAttribute("username", "Giovanni Hurtarte");
			        model.addAttribute("mode", appMode);
			        model.addAttribute("userLogin", new UserLogin());
			       // return "plantillaAdmin/index";
			        return "plantillaAdmin/indexUser";
		    }
		    
		    @RequestMapping("/inicioAdmin")
	    	public String adminAdmin(Model model, HttpSession session){
		    	   @SuppressWarnings("unchecked")
			
				    String nombreCompleto = (String) session.getAttribute("nombre");
				    
				    if (nombreCompleto == null) {
				    	nombreCompleto = new String();
					}	
		    	
		    	   
				    model.addAttribute("nombre", nombreCompleto);				
			        model.addAttribute("datetime", new Date());
			        model.addAttribute("username", "Giovanni Hurtarte");
			        model.addAttribute("mode", appMode);
			        model.addAttribute("userLogin", new UserLogin());
			       // return "plantillaAdmin/index";
			        return "plantillaAdmin/index";
		    }
	    	
	    	
		    
		    @PostMapping("/request") 
		  	public String  processlogin(@ModelAttribute UserLogin userLogin) {			  		  
	        User userResult = new User();	
	   	     userResult = iUser.findByUser(userLogin.getUser(), userLogin.getPassword());	   	     
	        if(!(userResult==null)) {	        	 
	             System.out.printf(userResult.getNombre().toString() + " "+userResult.getApellido().toString());
	        } else {
	        	System.out.println("No encontrado");
	        	return "plantillaAdmin/login";
	        }	          

	   	         
		     return  "plantillaAdmin/index"; 
		     }
		    
		    
		
		    
		    @RequestMapping(value = "/validate", method = RequestMethod.POST,produces = MediaType.TEXT_HTML_VALUE)
		    @ResponseBody
		    public String validateLogin(@RequestParam String user,@RequestParam String password,HttpServletRequest request) {
		    	
		    	@SuppressWarnings("unchecked")		
		    	String nombreCompleto = (String) request.getSession().getAttribute("nombre");
			
				
			   User userResult = new User();	
		   	   userResult = iUser.findByUser(user, password);	   	    
		   	    
		       if (!(userResult==null)) {	        	 
		             System.out.printf(userResult.getNombre().toString() + " "+userResult.getApellido().toString());
		             nombreCompleto = userResult.getNombre()+" "+userResult.getApellido();
		             request.getSession().setAttribute("nombre", nombreCompleto);
		             return "ok";
		        } else {
		        	System.out.println("No encontrado");
		        	return "errorusuario";
		        }
		        
		 
		    }
		    
		    @RequestMapping(value = "/getUser", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
		    @ResponseBody
		    public Map<String, String> getUser(@RequestParam int idUser,HttpServletRequest request) {		    	
		    	  User userResult = new User();	
		    	HashMap<String, String> map = new HashMap<>();
		 	   userResult = iUser.findByUser(idUser);	
		 	   String imgRostro =  Base64.getEncoder().encodeToString(userResult.getImgDpiRostro());
		 	   String imgDpiFrontal =  Base64.getEncoder().encodeToString(userResult.getImgDpiFrontal());
		 	   String imgDpiReverso =  Base64.getEncoder().encodeToString(userResult.getImgDpiReverso());
		 	   
		    	
		        map.put("nombre", userResult.getNombre());
		        map.put("apellido", userResult.getApellido());
		        map.put("correo", userResult.getCorreo());
		        map.put("dpi", String.valueOf(userResult.getDpi()));
		        map.put("nit", String.valueOf(userResult.getDpi()));
		        map.put("genero",userResult.getGenero());		      
		        map.put("telefono",userResult.getGenero());
		        map.put("imgRostro",imgRostro);
		        map.put("imgDpiFrontal",imgDpiFrontal);
		        map.put("imgDpiReverso",imgDpiReverso);
		        return map;
		    }
		    
		    
		    @RequestMapping(value = "/listUserPrincipalAdmin", method = RequestMethod.POST)
		    @ResponseBody
		    public String listUserPrincipalAdmin() {
		    	String response = null;
		    	GetJson getJson= new GetJson();
		    	
		    	response = getJson.getJsonDataTable( iUser.findAllTable());
		    	return response;
		    }
		    
		    
		    @RequestMapping(value = "/listOrderSale", method = RequestMethod.POST)
		    @ResponseBody
		    public String listOrderSale() {
		    	String response = null;
		    	GetJson getJson= new GetJson();		   
		    	response = getJson.getJsonDataTable(iOrden.dataTableFindByUserSell(3));
		    	return response;
		    }
		    
		    
		    @RequestMapping(value = "/listOrderBuy", method = RequestMethod.POST)
		    @ResponseBody
		    public String listOrderBuy() {
		    	String response = null;
		    	GetJson getJson= new GetJson();		   
		    	response = getJson.getJsonDataTable(iOrden.dataTableFindByUserBuy(3));
		    	return response;
		    }
		    
		    
		    
		    @RequestMapping(value = "/positiveValidation", method = RequestMethod.POST)
		    @ResponseBody
		    public String positiveValidation(@RequestParam int idUser,@RequestParam String email,@RequestParam String first,@RequestParam String last ) {
		    	int resp = iUser.updateUser(idUser);
		    	String password = PasswordGenerator.getPassword(5);
		    	String username = UserGenerator.getUser(first, last);
		    	iUser.updateUserNameAndPassword(username, password, idUser);		    	
		    	emailService.sendSimpleMessage(email, 
		        "Respuesta sobre la solicitud de usuario de Coincaex ", "Bienvenido a Coincaex  "+first+" "+
		    	"comienza a transformar tus finanzas, he invierte en criptomonedas. "+
		    	"\n\n\n"+
		        " Tu usuario es:  "+username+
		        "\n"+
		    	" y tu contraseña es:  "+password);
		    	return "Enviando respuesta";
		    }
		    
		    @RequestMapping(value = "/negativeValidation", method = RequestMethod.POST)
		    @ResponseBody
		    public String negativeValidation(@RequestParam int idUser,@RequestParam String email,@RequestParam String first,@RequestParam String last,@RequestParam String message) {
		    	System.out.println("Rechazado");
		    	emailService.sendSimpleMessage(email, 
		        "Respuesta sobre la solicitud de usuario de Coincaex ", "Estimado  "+first+" :"+"\n\n"+
		    	"El administrador le ha denegado la solicitud de la creación de usuario, "
		    	+ "debido a que no cumple con los requisitos de los documentos solicitados o "
		    	+ "bien, hay algún problema con sus datos personales. "+
		    	"\n\n"+message);
		    	
		    	return "Enviando respuesta";
		    }
		    
		    
		    @PostMapping("/destroy")
			public String destroySession(HttpServletRequest request) {
		    	System.out.println("Destroy");
				request.getSession().invalidate();
				return "redirect:/";
			}
		    
		    
		
		    
		
	
		 
	
}
