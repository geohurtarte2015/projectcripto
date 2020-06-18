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
import com.renovaus.model.bancos.IBanco;
import com.renovaus.model.billetera.IBilletera;
import com.renovaus.model.billetera_exchange.IBilleteraExchange;
import com.renovaus.model.orden.IOrden;
import com.renovaus.model.user.IUser;
import com.renovaus.pojo.Admin;
import com.renovaus.pojo.Banco;
import com.renovaus.pojo.Billetera;
import com.renovaus.pojo.BilleteraExchange;
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
	  
			@Autowired
	  		private IBilletera iBilletera;
			
			@Autowired
	  		private IBanco iBanco;
			
			@Autowired
	  		private IBilleteraExchange iBilleteraExchange;
	  		
	  
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
		    
		    
		    @RequestMapping("/buyCripto")
	    	public String buyCripto(Model model, HttpSession session){
		    @SuppressWarnings("unchecked")
		    String nombreCompleto = (String) session.getAttribute("nombre");
		    String idUserSession = (String) session.getAttribute("idUserSession");
			
				if (nombreCompleto == null) {
					nombreCompleto = new String();
				}

				if (idUserSession == null) {
					idUserSession = new String();
				}
				
				
				List<Billetera> billeteras = iBilletera.findByUser(3);
		
				model.addAttribute("nombre", nombreCompleto);
				model.addAttribute("idUserSession", idUserSession);
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
					
				
				if (nombreCompleto == null) {
				    nombreCompleto = new String();
				}	
				
				if (idUserSession == null) {
					idUserSession = new String();
				}
				
				
				List<Banco> bancos = iBanco.findAll();
				
			
				model.addAttribute("nombre", nombreCompleto);	
				model.addAttribute("idUserSession", idUserSession);
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
			    			           +numBilletera+" "+voucher.getOriginalFilename());
			    	 
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
			    					 Integer.valueOf(monedaCripto)			    				
			    					 )
			    					 );
			    					 
			    			 
			    			 
			    
			  
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
		    		@RequestParam String idUser)		    
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
			    					 nombreCuenta
			    					 )
			    					 );
			    					 
			    			 
			    			 
			    
			  
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
		    	   
				    if (nombreCompleto == null) {
				    	nombreCompleto = new String();
				    	idUserSession = new String();
					}	
		    	
		    	   
				    model.addAttribute("nombre", nombreCompleto);
				    model.addAttribute("idUserSession", idUserSession);				
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
		    	String idUserSession = (String) request.getSession().getAttribute("idUserSession");
			
				
			   User userResult = new User();	
		   	   userResult = iUser.findByUser(user, password);	   	    
		   	    
		       if (!(userResult==null)) {	        	 
		             System.out.printf(userResult.getNombre().toString() + " "+userResult.getApellido().toString());
		             nombreCompleto = userResult.getNombre()+" "+userResult.getApellido();
		             idUserSession = String.valueOf(userResult.getId());
		             
		             request.getSession().setAttribute("nombre", nombreCompleto);
		             request.getSession().setAttribute("idUserSession", idUserSession);
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
		        map.put("telefono",String.valueOf(userResult.getTelefono()));
		        map.put("imgRostro",imgRostro);
		        map.put("imgDpiFrontal",imgDpiFrontal);
		        map.put("imgDpiReverso",imgDpiReverso);
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
		    
		    
		    @RequestMapping(value = "/saveWallet", method = RequestMethod.POST)
		    @ResponseBody
		    public String saveWallet(HttpSession session,@RequestParam String descripcion,@RequestParam String direccion ) {
		    	String idUserSession = (String) session.getAttribute("idUserSession");
				
				if (idUserSession == null) {
					idUserSession = new String();
				}
		    	
		    	
		    	int res = iBilletera.save(new Billetera(direccion,descripcion,Integer.parseInt(idUserSession)));
		    	
		    	return "Registrado";
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
		    
		    @RequestMapping(value = "/positiveBuy", method = RequestMethod.POST)
		    @ResponseBody
		    public String positiveBuy(@RequestParam String monedaCripto,@RequestParam int idOrder,@RequestParam String cripto,@RequestParam String email,@RequestParam String first,@RequestParam String last ) {
		    	if(cripto.isEmpty() || cripto==null) {
		    		cripto="0.00";
		    	}
		    	
		    	int respEstado = iOrden.updateEstado(3, idOrder);		    	
		    	int respFiat = iOrden.updateCripto(new BigDecimal(cripto), idOrder);
		    		    	
		    	emailService.sendSimpleMessage(email, 
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
		    		    	
		    	emailService.sendSimpleMessage(email, 
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
		    	iUser.updateEstado(idUser, 2);
		    	emailService.sendSimpleMessage(email, 
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
		    	emailService.sendSimpleMessage(email, 
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
		    	emailService.sendSimpleMessage(email, 
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
