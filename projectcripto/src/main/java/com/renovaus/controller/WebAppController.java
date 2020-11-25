package com.renovaus.controller;

import org.hibernate.Hibernate;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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
import com.renovaus.configuration.ConfigProperties;
import com.renovaus.model.admin.IAdmin;
import com.renovaus.model.bancos.IBanco;
import com.renovaus.model.billetera.IBilletera;
import com.renovaus.model.billetera_exchange.IBilleteraExchange;
import com.renovaus.model.comision.IComision;
import com.renovaus.model.orden.IOrden;
import com.renovaus.model.report.IReport;
import com.renovaus.model.user.IUser;
import com.renovaus.pojo.Admin;
import com.renovaus.pojo.AdminLogin;
import com.renovaus.pojo.Banco;
import com.renovaus.pojo.Billetera;
import com.renovaus.pojo.BilleteraExchange;
import com.renovaus.pojo.Comision;
import com.renovaus.pojo.Estado;
import com.renovaus.pojo.EstadoOrden;
import com.renovaus.pojo.Orden;
import com.renovaus.pojo.TipoNegocio;
import com.renovaus.pojo.TipoPago;
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
	  
	  		private EmailToWebservice emailToWebservice = new EmailToWebservice();
	  		
	  		@Autowired
	  		private IReport iReport;
	  
	  		@Autowired
	  		private IComision icomision;
	  		
			@Autowired
	  		private IBilletera iBilletera;
			
			@Autowired
	  		private IBanco iBanco;
			
			@Autowired
	  		private IBilleteraExchange iBilleteraExchange;
	  		
	  
	  		@Autowired
	  		private IUser iUser;
	  		
	  		@Autowired
	  		private IAdmin iAdmin;
	  		
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
		    String emailUser = (String) session.getAttribute("emailUser");
		    String apellido = (String) session.getAttribute("apellido");
		    
		    
				
			
			if (nombreCompleto == null) {
			    nombreCompleto = new String();
			}	
			
			model.addAttribute("nombre", nombreCompleto);		
			model.addAttribute("emailUser", emailUser);		
	        model.addAttribute("datetime", new Date());
	        model.addAttribute("username", "Giovanni Hurtarte");
	        model.addAttribute("mode", appMode);
	        model.addAttribute("userLogin", new UserLogin());
	        return "plantillaAdmin/login";
		    }
		    
		    
		    @RequestMapping("/loginAdmin")
	    	public String indexLoginAdmin(Model model, HttpSession session){
		    @SuppressWarnings("unchecked")
		    String nombreCompletoAdmin = (String) session.getAttribute("nombreAdmin");
				
			
			if (nombreCompletoAdmin == null) {
			    nombreCompletoAdmin = new String();
			}	
			
			model.addAttribute("nombreAdmin", nombreCompletoAdmin);		
	        model.addAttribute("datetime", new Date());
	        model.addAttribute("username", "Jose Guillen");
	        model.addAttribute("mode", appMode);
	        model.addAttribute("userLogin", new UserLogin());
	        return "plantillaAdmin/loginAdmin";
		    }
		    
		    @RequestMapping("/report")
	    	public String report(Model model, HttpSession session){
		    @SuppressWarnings("unchecked")
		    String nombreCompletoAdmin = (String) session.getAttribute("nombreAdmin");
				
			
			if (nombreCompletoAdmin == null) {
			    nombreCompletoAdmin = new String();
			}	
			
			model.addAttribute("nombreAdmin", nombreCompletoAdmin);		
	        model.addAttribute("datetime", new Date());
	        model.addAttribute("username", "Jose Guillen");
	        model.addAttribute("mode", appMode);
	        model.addAttribute("userLogin", new UserLogin());
	        return "plantillaAdmin/report";
		    }
		    
		    
		    
		    @RequestMapping("/buyCripto")
	    	public String buyCripto(Model model, HttpSession session){
		    @SuppressWarnings("unchecked")
		    String nombreCompleto = (String) session.getAttribute("nombre");
		    String idUserSession = (String) session.getAttribute("idUserSession");
		    String emailUser = (String) session.getAttribute("emailUser");
			
				if (nombreCompleto == null) {
					nombreCompleto = new String();
				}

				if (idUserSession == null) {
					idUserSession = new String();
				}
				
				if (emailUser == null) {
					emailUser = new String();
				}
				
				
				Comision comision = icomision.findById(1);
			 	String valComision=String.valueOf(comision.getComisionCompra());
			 	
				
				List<Billetera> billeteras = iBilletera.findByUser(Integer.parseInt(idUserSession));
				
			
				model.addAttribute("valComision", valComision);
				model.addAttribute("nombre", nombreCompleto);
				model.addAttribute("idUserSession", idUserSession);
				model.addAttribute("emailUser", emailUser);
				model.addAttribute("datetime", new Date());
				model.addAttribute("username", "Giovanni Hurtarte");
				model.addAttribute("billeteras",billeteras);
				model.addAttribute("mode", appMode);
				model.addAttribute("userLogin", new UserLogin());
				return "plantillaAdmin/buyCripto";

		    }
	
		    
		    
			@RequestMapping("/sellCripto")
		    public String sellCripto(Model model, HttpSession session){
			    @SuppressWarnings("unchecked")
			    String nombreCompleto = (String) session.getAttribute("nombre");
			    String idUserSession = (String) session.getAttribute("idUserSession");
			    String emailUser = (String) session.getAttribute("emailUser");
					
				
				if (nombreCompleto == null) {
				    nombreCompleto = new String();
				}	
				
				if (idUserSession == null) {
					idUserSession = new String();
				}
				

				if (emailUser == null) {
					emailUser = new String();
				}
				
				
				List<Banco> bancos = iBanco.findAll();
				
				Comision comision = icomision.findById(1);
			 	String valComision=String.valueOf(comision.getComisionVenta());
			 	
				
			 	
				model.addAttribute("nombre", nombreCompleto);	
				model.addAttribute("valComision", valComision);
				model.addAttribute("idUserSession", idUserSession);
				model.addAttribute("emailUser", emailUser);
				model.addAttribute("datetime", new Date());
				model.addAttribute("username", "Giovanni Hurtarte");
				model.addAttribute("bancos",bancos);
				model.addAttribute("mode", appMode);
				model.addAttribute("userLogin", new UserLogin());
	        return "plantillaAdmin/sellCripto";
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
		    
		    @RequestMapping("/logoutAdmin")
	    	public String logoutAdmin(Model model, HttpSession session){
		    @SuppressWarnings("unchecked")
		    String nombreCompletoAdmin = (String) session.getAttribute("nombreAdmin");
			if (nombreCompletoAdmin == null) {
			    nombreCompletoAdmin = new String();
			}	
			
			model.addAttribute("nombreAdmin", nombreCompletoAdmin);		

	        return "plantillaAdmin/logoutAdmin";
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
		         
		         emailToWebservice.sendToPhp(email, 
		 		        "Solicitud enviada a Coincaex ", "Hola  "+nombre+" "+
		 		    	"tu solicitud a sido enviada para calificar a la creación del usuario. "+
		 		    	"\n\n\n");
		         
		      }
		      catch (Exception e) {
		        System.out.println(e.getMessage());
		        return "Error en carga de datos";
		      }
		      
		      return "Datos Cargados correctamente";
		    } 
		    
		    
		    @RequestMapping(value = "/uploadFileBuy", method = RequestMethod.POST,produces = MediaType.TEXT_HTML_VALUE)
		    @ResponseBody
		    public String uploadFileBuy(
			    	@RequestParam String valorFiat,
	    			@RequestParam String monedaFiat,
	    			@RequestParam String tipoPago,
	    			@RequestParam String numTarjetaCredito,
	    			@RequestParam String codigoCvv,
	    			@RequestParam String fechaVencimiento,
		    		@RequestParam String numBilletera,	
		    		@RequestParam String monedaCripto,	  
		    		@RequestParam String tipoNegocio,	
		    		@RequestParam String idUser,
		    		@RequestParam String emailUser,
		    		@RequestParam String nameUser,
			        @RequestParam("voucher") MultipartFile voucher) 
			    	{
			    
		    	
		    	 byte[] blobVoucher = null;
		    	 String datoTarjetaCredito = "";
		    	 
		    	 if(!numTarjetaCredito.isEmpty()) {
		    	 datoTarjetaCredito = numTarjetaCredito + "|" + codigoCvv + "|" + fechaVencimiento;
		    	 }
		    	 
		    	if(voucher.getOriginalFilename().isEmpty()) {
		    		log.warn("[IMAGE LOAD IS NULL]");
		    		
		    	}else {		    		
		    		try {
						blobVoucher = InputStreamConvert.readBytesFromFile(voucher);
					} catch (IOException e) {
						log.error("[ERROR READ IMAGE]"+" "+e);
					}
				       
		    		
		    	}
		    	
			      try {
			    	  
			    	 System.out.println(valorFiat+" "+monedaFiat+" "+tipoPago+" "+numTarjetaCredito+" "+codigoCvv+" "+fechaVencimiento+" "
			    			           +numBilletera+" "+voucher.getOriginalFilename()+" "+emailUser);
			    	 
			    	  BigDecimal valorFiatBig = new BigDecimal(valorFiat); 
			    	  BigDecimal valorCriptoBig = new BigDecimal("0"); 
			    	 // Integer bank = new Integer(null);
			    	 
			    	 iOrden.saveBuy(
			    			 new Orden(
			    					 new TipoPago(Integer.valueOf(tipoPago)),
			    					 new TipoNegocio(Integer.valueOf(tipoNegocio)),
			    					 blobVoucher,
			    					 valorFiatBig,
			    					 valorCriptoBig,
			    					 new User(Integer.valueOf(idUser)),
			    					 new EstadoOrden(1),
			    					 null,
			    					 datoTarjetaCredito,			    			
			    					 Integer.valueOf(numBilletera),
			    					 Integer.valueOf(monedaFiat),
			    					 Integer.valueOf(monedaCripto),
			    					 1//Comision
			    					 )
			    					 );
			    	 
			    	 
			    	 emailToWebservice.sendToPhp(emailUser, 
					 	        "Solicitud de compra enviada a Coincaex ", "Hola  "+nameUser+" "+
					 		    	"tu solicitud de compra a sido enviada, pronto tendras la respuesta de tu solicitud. "+
					 	    	"\n\n\n");
			    					 
			    			 
			    			 
			    
			  
			      }
			      catch (Exception e) {
			        System.out.println(e.getMessage());
			        return "Error en carga de datos";
			      }
			      
			      return "Datos Cargados correctamente";
			    } 
		    
		    		    
		    @RequestMapping(value = "/uploadFileSell", method = RequestMethod.POST,produces = MediaType.TEXT_HTML_VALUE)
		    @ResponseBody
		    public String uploadFileSell(
			    	@RequestParam String valorCripto,
	    			@RequestParam String tipoCripto,
	    			@RequestParam String numeroCuenta,
	    			@RequestParam String tipoCuenta,
	    			@RequestParam String banco,
	    			@RequestParam String tipoFiat,		  
		    		@RequestParam String tipoNegocio,	
		    		@RequestParam String nombreCuenta,
		    		@RequestParam String idUser,
		    		@RequestParam String nameUser,
		    		@RequestParam String emailUser
		    		)		    
			    	{
			    
		    
		    	
			      try {
			    	  
			    	 System.out.println(" valor cripto: "+valorCripto+" tip cripto:"+tipoCripto+" numero Cuenta:"+numeroCuenta+
			    			 			" tipo Cuenta:"+tipoCuenta+" Banco:"+banco+" Tipo Fiat:"+tipoFiat+
			    			 			" tipo Negocio:"+tipoNegocio+" User:"+idUser+" Nombre Cuenta:"+nombreCuenta);
			    	 
			    	  BigDecimal valorFiatBig = new BigDecimal("0"); 
			    	  BigDecimal valorCriptoBig = new BigDecimal(valorCripto); 
			    	 // Integer bank = new Integer(null);
			    	 
			    	 iOrden.saveSell(
			    			 new Orden(			    			
			    					 new TipoNegocio(Integer.valueOf(tipoNegocio)),			    				
			    					 valorFiatBig,
			    					 valorCriptoBig,
			    					 new User(Integer.valueOf(idUser)),
			    					 new EstadoOrden(1),
			    					 tipoCuenta,
			    					 numeroCuenta,		
			    					 Integer.valueOf(banco),			    				
			    					 Integer.valueOf(tipoFiat),
			    					 Integer.valueOf(tipoCripto),
			    					 nombreCuenta,
			    					 1 //comision venta
			    					 )
			    					 );
			    	 
			    	 emailToWebservice.sendToPhp(emailUser, "Solicitud de venta enviada a Coincaex ", "Hola  "+nameUser+" "+
				 		    	"tu solicitud de venta a sido enviada, pronto tendras la respuesta de tu solicitud. "+
				 	    	"\n\n\n");
			    	 

			    
			  
			      }
			      catch (Exception e) {
			        System.out.println(e.getMessage());
			        return "Error en carga de datos";
			      }
			      
			      return "Solicitud enviada correctamente";
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
		    	   String idUserSession = (String) session.getAttribute("idUserSession");
		    	   String emailUser = (String) session.getAttribute("emailUser");
		    	   
		    	   System.out.println("Email Inicio "+emailUser+"\nNombre inicio "+nombreCompleto);
		    	   
				    if (nombreCompleto == null) {
				    	nombreCompleto = new String();
				    	idUserSession = new String();
				    	emailUser = new String();
					}	
		    	
		    	   
				    model.addAttribute("nombre", nombreCompleto);
				    model.addAttribute("idUserSession", idUserSession);		
				    model.addAttribute("emailUser", emailUser);		
			        model.addAttribute("datetime", new Date());
			        model.addAttribute("username", "Giovanni Hurtarte");
			        model.addAttribute("mode", appMode);
			        model.addAttribute("userLogin", new UserLogin());
			       // return "plantillaAdmin/index";
			        return "plantillaAdmin/indexUser";
		    }
		    
		    
		    @RequestMapping("/inicioAdmin")
	    	public String adminAdmin(Model model, HttpSession session,HttpServletRequest request){
		    	   @SuppressWarnings("unchecked")
			
		    	 	String nombreCompletoAdmin = (String) request.getSession().getAttribute("nombreAdmin");
			    	String idAdminSession = (String) request.getSession().getAttribute("idAdminSession");
				    
				    if (nombreCompletoAdmin == null) {
				    	nombreCompletoAdmin = new String();
					}	
				    
				    request.getSession().setAttribute("nombre", nombreCompletoAdmin);
		            request.getSession().setAttribute("idAdminSession", idAdminSession);
		    	   
		            model.addAttribute("nombre", nombreCompletoAdmin);
				    model.addAttribute("idAdminSession", idAdminSession);	
			        model.addAttribute("datetime", new Date());
			        model.addAttribute("username", "Jose Guillen");
			        model.addAttribute("mode", appMode);
			        model.addAttribute("userLogin", new AdminLogin());
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
		    	String idUserSession = (String) request.getSession().getAttribute("idUserSession");
		    	String emailUser = (String) request.getSession().getAttribute("emailUser");
		    	
		    	
			
				
			   User userResult = new User();	
		   	   userResult = iUser.findByUser(user, password);	   	    
		   	
		   	   
		       if (!(userResult==null)) {	        	 
		    	   
				   	  System.out.println("Nombre completo: "+userResult.getNombre()+"\nEmail: "+userResult.getCorreo());
				   	  
		             nombreCompleto = userResult.getNombre()+" "+userResult.getApellido();
		             idUserSession = String.valueOf(userResult.getId());
		             emailUser=String.valueOf(userResult.getCorreo());
		             
		             request.getSession().setAttribute("nombre", nombreCompleto);
		             request.getSession().setAttribute("idUserSession", idUserSession);
		             request.getSession().setAttribute("emailUser", emailUser);
		             
		             return "ok";
		        } else {
		        	System.out.println("No encontrado");
		        	return "errorusuario";
		        }
		        
		 
		    }
		    
		    
		    @RequestMapping(value = "/validateAdmin", method = RequestMethod.POST,produces = MediaType.TEXT_HTML_VALUE)
		    @ResponseBody
		    public String validateLoginAdmin(@RequestParam String user,@RequestParam String password,HttpServletRequest request) {
		    	
		    	@SuppressWarnings("unchecked")		
		    	String nombreCompletoAdmin = (String) request.getSession().getAttribute("nombreAdmin");
		    	String idAdminSession = (String) request.getSession().getAttribute("idAdminSession");
			
			
		       
			   Admin adminResult = new Admin();	
			   adminResult = iAdmin.findByAdmin(user, password);	   	    
		   	    
		       if (!(adminResult==null)) {	        	 
		             System.out.printf(adminResult.getNombre().toString() + " "+adminResult.getApellido().toString());
		             nombreCompletoAdmin = adminResult.getNombre()+" "+adminResult.getApellido();
		             
		             idAdminSession = String.valueOf(adminResult.getId());
		             
		             request.getSession().setAttribute("nombreAdmin", nombreCompletoAdmin);
		             request.getSession().setAttribute("idAdminSession", idAdminSession);
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
		        map.put("nit", String.valueOf(userResult.getNit()));
		        map.put("genero",userResult.getGenero());		      
		        map.put("telefono",String.valueOf(userResult.getTelefono()));
		        map.put("imgRostro",imgRostro);
		        map.put("imgDpiFrontal",imgDpiFrontal);
		        map.put("imgDpiReverso",imgDpiReverso);
		        return map;
		    }
		    
		    

		    @RequestMapping(value = "/getUpdateOrder", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
		    @ResponseBody
		    public Map<String, String> getUpdateStateOrder(@RequestParam int idOrden,HttpServletRequest request) {		    	
		    	  User userResult = new User();	
		    	HashMap<String, String> map = new HashMap<>();		    	
		 	   	int respUpdate= iOrden.updateEstado(6, idOrden);	
		        map.put("response", String.valueOf(respUpdate));
		 
		        return map;
		    }
		    
		    @RequestMapping(value = "/getOrderBuy", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
		    @ResponseBody
		    public Map<String, String> getOrderBuy(@RequestParam int idOrder, HttpServletRequest request) {		
		    	
		    	Orden orderResult = new Orden();	
		    	HashMap<String, String> map = new HashMap<>();		    	
		    	orderResult = iOrden.findByOrderBuy(idOrder);	    
		 	   	String imgVoucher =  Base64.getEncoder().encodeToString(orderResult.getImagenVoucher());
		 	   	
		 	    
		 	    map.put("nombre", orderResult.getUser().getNombre());
		        map.put("apellido", orderResult.getUser().getApellido());
		        map.put("correo", orderResult.getUser().getCorreo());
		        map.put("fiat", String.valueOf(orderResult.getFiat()));
		        map.put("moneda_fiat", orderResult.getMonedaFiatObject().getDescripcion());
		        map.put("voucher", imgVoucher);
		        map.put("billetera", orderResult.getBilleteraDireccion().getDireccion());
		        map.put("criptomoneda", String.valueOf(orderResult.getCripto()));
		        map.put("moneda_cripto",orderResult.getMonedaCriptoObject().getDescripcion());	 
		        return map;
		    }
		    
		    
		    @RequestMapping(value = "/getWalletType", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
		    @ResponseBody
		    public  String  getWalletType(@RequestParam String idUser,@RequestParam String idCrypto, HttpServletRequest request) throws JSONException {		
		    	
		    	
		    	JSONArray jsonArray = new JSONArray();
		    	
		    	HashMap<String, String> map = new HashMap<>();		
		    	
		    	List<String[]> list  = iBilletera.findByUserType(Integer.parseInt(idUser), Integer.parseInt(idCrypto));
		    	int size = list.size();
		    	
		    	for (int x = 0; x < size; x++) {
		    	      JSONObject json = new JSONObject();
		    	      String[] mainPack = (String[])list.get(x);
		    	      json.put(mainPack[0], mainPack[1]);
		    	      jsonArray.put(json);
		    	    }
		    	
		    
		        return jsonArray.toString();
		    }
		    
		    
		    
		    
		    @RequestMapping(value = "/getOrderSell", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
		    @ResponseBody
		    public Map<String, String> getOrderSell(@RequestParam int idOrder,HttpServletRequest request) {	
		    	Orden orderResult = new Orden();	
		    	HashMap<String, String> map = new HashMap<>();		    	
		    	orderResult = iOrden.findByOrderSell(idOrder);	
		    	
		 	    map.put("nombre", orderResult.getUser().getNombre());
		        map.put("apellido", orderResult.getUser().getApellido());
		        map.put("correo", orderResult.getUser().getCorreo());
		        map.put("fiat", String.valueOf(orderResult.getFiat().toPlainString()));
		        map.put("cuenta", orderResult.getCuentaBancaria());
		        map.put("tipo_cuenta", orderResult.getTipoCuentaBancaria());
		        map.put("moneda_fiat", orderResult.getMonedaFiatObject().getDescripcion());			
		        map.put("cripto", String.valueOf(orderResult.getCripto().toPlainString()));
		        map.put("moneda_cripto",orderResult.getMonedaCriptoObject().getDescripcion());	 
		        map.put("banco", orderResult.getBanco().getDescripcion());
		        map.put("nombre_cuenta", orderResult.getNombreCuentaBancaria());
		        return map;
		    }
		    
		    
		    
		    
		    @RequestMapping(value = "/getAddressQr", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
		    @ResponseBody
		    public Map<String, String> getAddressQr(@RequestParam int idCripto,HttpServletRequest request) {		    	
		    	  BilleteraExchange billteraExchange = new BilleteraExchange();	
		    	  
		    	HashMap<String, String> map = new HashMap<>();
		    	
		    	
		 	   billteraExchange = iBilleteraExchange.findByBilleteraExchange(idCripto);
		       
		        map.put("direccion", String.valueOf(billteraExchange.getDireccion()));
		        map.put("imagen", String.valueOf(billteraExchange.getImagen()));
		 
		

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
		    
		    
		 
		    
		    
		    @RequestMapping(value = "/listCriptoAddress", method = RequestMethod.POST)
		    @ResponseBody
		    public String listCriptoAddress(HttpSession session) {
		    	
		    	String idUserSession = (String) session.getAttribute("idUserSession");
				
				if (idUserSession == null) {
					idUserSession = new String();
				}
		    	
		    	String response = null;
		    	GetJson getJson= new GetJson();		   
		    	response = getJson.getJsonDataTable(iBilletera.dataTableFindByUser(Integer.parseInt(idUserSession)));
		    	return response;
		    }
		    
		    
		    
		    @RequestMapping(value = "/listOrderBuy", method = RequestMethod.POST)
		    @ResponseBody
		    public String listOrderBuy(HttpSession session) {
		    	String idUserSession = (String) session.getAttribute("idUserSession");
				
				if (idUserSession == null) {
					idUserSession = new String();
				}
		    	
		    	String response = null;
		    	GetJson getJson= new GetJson();		   
		    	response = getJson.getJsonDataTable(iOrden.dataTableFindByUserBuy(Integer.parseInt(idUserSession)));
		    	return response;
		    }
		    
		    @RequestMapping(value = "/listOrderBuyAdmin", method = RequestMethod.POST)
		    @ResponseBody
		    public String listOrderBuyAdmin(HttpSession session) {
		    	String idUserSession = (String) session.getAttribute("idUserSession");
				
				if (idUserSession == null) {
					idUserSession = new String();
				}
		    	
		    	String response = null;
		    	GetJson getJson= new GetJson();		   
		    	response = getJson.getJsonDataTable(iOrden.dataTableFindByUserBuyAdmin());
		    	return response;
		    }
		    
		    
		    @RequestMapping(value = "/listOrderSale", method = RequestMethod.POST)
		    @ResponseBody
		    public String listOrderSale(HttpSession session) {
		    	
		  
				String idUserSession = (String) session.getAttribute("idUserSession");
				
				if (idUserSession == null) {
					idUserSession = new String();
				}
		    	
		    	String response = null;
		    	GetJson getJson= new GetJson();		   
		    	response = getJson.getJsonDataTable(iOrden.dataTableFindByUserSell(Integer.parseInt(idUserSession)));
		    	return response;
		    }
		    
		    @RequestMapping(value = "/listOrderSaleAdmin", method = RequestMethod.POST)
		    @ResponseBody
		    public String listOrderSaleAdmin(HttpSession session) {
		    	
		  
				String idUserSession = (String) session.getAttribute("idUserSession");
				
				if (idUserSession == null) {
					idUserSession = new String();
				}
		    	
		    	String response = null;
		    	GetJson getJson= new GetJson();		   
		    	response = getJson.getJsonDataTable(iOrden.dataTableFindByUserSellAdmin());
		    	return response;
		    }
		    
		    @RequestMapping(value = "/listAllReport", method = RequestMethod.POST)
		    @ResponseBody
		    public String listAllReport(@RequestParam String initTime,@RequestParam String finishTime,HttpSession session) {
		    	
		  
				String idUserSession = (String) session.getAttribute("idUserSession");
				System.out.println("initTime----->   "+initTime+" finishTime----->   "+finishTime);
				
				if (idUserSession == null) {
					idUserSession = new String();
				}
		    	
		    	String response = null;
		    	GetJson getJson= new GetJson();		   
		    	response = getJson.getJsonDataTable(iReport.dataTableAll(initTime,finishTime));
		    	return response;
		    }
		    
		    
		    @RequestMapping(value = "/saveWallet", method = RequestMethod.POST)
		    @ResponseBody
		    public String saveWallet(HttpSession session,@RequestParam String descripcion,@RequestParam String direccion,String idCrypto ) {
		    	String idUserSession = (String) session.getAttribute("idUserSession");
				
				if (idUserSession == null) {
					idUserSession = new String();
				}
		    	
		    	
		    	int res = iBilletera.save(new Billetera(direccion,descripcion,Integer.parseInt(idUserSession),Integer.parseInt(idCrypto)));
		    	
		    	return "Registrado";
		    }
		    
		    
		    @RequestMapping(value = "/positiveValidation", method = RequestMethod.POST)
		    @ResponseBody
		    public String positiveValidation(@RequestParam int idUser,@RequestParam String email,@RequestParam String first,@RequestParam String last ) {
		    	int resp = iUser.updateUser(idUser);
		    	String password = PasswordGenerator.getPassword(5);
		    	String username = UserGenerator.getUser(first, last);
		    	iUser.updateUserNameAndPassword(username, password, idUser);	
		    	
		    	 emailToWebservice.sendToPhp(email, 
		        "Respuesta sobre la solicitud de usuario de Coincaex ", "Bienvenido a Coincaex  "+first+" "+
		    	"comienza a transformar tus finanzas, he invierte en criptomonedas. "+
		    	"\n\n\n"+
		        " Tu usuario es:  "+username+
		        "\n"+
		    	" y tu contraseña es:  "+password);
		    	return "Respuesta Enviada";
		    }
		    
		    @RequestMapping(value = "/positiveBuy", method = RequestMethod.POST)
		    @ResponseBody
		    public String positiveBuy(@RequestParam String monedaCripto,@RequestParam int idOrder,@RequestParam String cripto,@RequestParam String email,@RequestParam String first,@RequestParam String last ) {
		    	if(cripto.isEmpty() || cripto==null) {
		    		cripto="0.00";
		    	}
		    	
		    	int respEstado = iOrden.updateEstado(3, idOrder);		    	
		    	int respFiat = iOrden.updateCripto(new BigDecimal(cripto), idOrder);
		    		    	
		    	 emailToWebservice.sendToPhp(email, 
		        "Respuesta sobre la solicitud de compra en Coincaex ", "Hola  "+first+" "+
		    	"tu compra fue aprobada, recibiste . "+
		    	"\n\n"+
		        " "+cripto+" "+monedaCripto+"  en tu billetera registrada"+
		        "\n\n"+
		    	" gracias por tu compra.  ");
		    	return "Enviando respuesta";
		    }
		    
		    @RequestMapping(value = "/positiveSell", method = RequestMethod.POST)
		    @ResponseBody
		    public String positiveSell(@RequestParam String monedaFiat,@RequestParam int idOrder,@RequestParam String fiat,@RequestParam String email,@RequestParam String first,@RequestParam String last) {
		    	int respEstado = iOrden.updateEstado(3, idOrder);		    	
		    	int respFiat = iOrden.updateFiat(new BigDecimal(fiat), idOrder);
		    		    	
		    	 emailToWebservice.sendToPhp(email, 
		        "Respuesta sobre la solicitud de venta en Coincaex ", "Hola  "+first+" "+
		    	"tu solicitud de venta fue aprobada, recibiste . "+
		    	"\n\n"+
		        " "+fiat+" "+monedaFiat+"  en la cuenta bancaria registrada"+
		        "\n\n"+
		    	" puedes solicitar mas cuando desees.  ");
		    	return "Enviando respuesta";
		    }
		    
		    
		    
		    @RequestMapping(value = "/negativeValidation", method = RequestMethod.POST)
		    @ResponseBody
		    public String negativeValidation(@RequestParam int idUser,@RequestParam String email,@RequestParam String first,@RequestParam String last,@RequestParam String message) {
		    	System.out.println("Rechazado");
		    	iUser.updateEstado(idUser, 3);//Estado 3 es rechazado
		    	 emailToWebservice.sendToPhp(email, 
		        "Respuesta sobre la solicitud de usuario de Coincaex ", "Estimado  "+first+" :"+"\n\n"+
		    	"El administrador le ha denegado la solicitud de la creación de usuario, "
		    	+ "debido a que no cumple con los requisitos de los documentos solicitados o "
		    	+ "bien, hay algún problema con sus datos personales. "+
		    	"\n\n"+message);
		    	
		    	return "Enviando respuesta";
		    }
		    
		    @RequestMapping(value = "/negativeBuy", method = RequestMethod.POST)
		    @ResponseBody
		    public String negativeBuy(@RequestParam String idOrder,@RequestParam String email,@RequestParam String first,@RequestParam String last,@RequestParam String message) {
		    	System.out.println("Rechazado");
		    	iOrden.updateEstado(3, Integer.parseInt(idOrder));
		    	 emailToWebservice.sendToPhp(email, 
		        "Respuesta sobre la solicitud de compra de criptoactivos en Coincaex ", "Estimado/a  "+first+" :"+"\n\n"+
		    	"El administrador le ha denegado la solicitud de la compra de criptoactivos, "
		    	+ "debido a que no cumple con los requisitos de la información solicitada o "
		    	+ "bien, hay algún problema con sus datos personales. "+
		    	"\n\n"+"*Observaciones:"+"\n"+message);
		    	
		    	return "Enviando respuesta";
		    }
		    
		    @RequestMapping(value = "/negativeSell", method = RequestMethod.POST)
		    @ResponseBody
		    public String negativeSell(@RequestParam String idOrder,@RequestParam String email,@RequestParam String first,@RequestParam String last,@RequestParam String message) {
		    	System.out.println("Rechazado");
		    	iOrden.updateEstado(3, Integer.parseInt(idOrder));
		    	 emailToWebservice.sendToPhp(email, 
		    	"Respuesta sobre la solicitud de venta de criptoactivos en Coincaex ", "Estimado/a  "+first+" :"+"\n\n"+
		    	"El administrador le ha denegado la solicitud de venta de criptoactivos, "
		    	+ "debido a que no cumple con los requisitos de la información solicitada o "
		    	+ "bien, hay algún problema con sus datos personales. "+
		    	"\n\n"+"*Observaciones:"+"\n"+message);
		    	
		    	return "Enviando respuesta";
		    }
		    
		    
		    @PostMapping("/destroy")
			public String destroySession(HttpServletRequest request) {
		    	System.out.println("Destroy");
				request.getSession().invalidate();
				return "redirect:/";
			}
		    
		    
		
		    
		
	
		 
	
}
