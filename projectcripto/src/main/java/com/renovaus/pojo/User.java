package com.renovaus.pojo;

import java.math.BigInteger;
import java.sql.Blob;
import java.util.Date;

public class User extends Persona {
	
	private String nit;
	private BigInteger  dpi;
	private byte[]  imgDpiRostro;
	private byte[]  imgDpiFrontal;	
	private byte[]  imgDpiReverso;
	private Admin admin;
	
	
	public User(String nombre, String apellido, String genero, int telefono, String usuario,
			String password, Estado estado, String nit, BigInteger dpi, byte[] imgDpiRostro, byte[] imgDpiFrontal,
			byte[] imgDpiReverso, Admin admin, String correo) {
		super(nombre, apellido, genero, telefono, usuario, password, estado,correo);
		this.nit = nit;
		this.dpi = dpi;
		this.imgDpiRostro = imgDpiRostro;
		this.imgDpiFrontal = imgDpiFrontal;
		this.imgDpiReverso = imgDpiReverso;
		this.admin = admin;
	}
	
	public User(int id,String nombre, String apellido, String genero, int telefono, String usuario,
			String password, Estado estado, String nit, BigInteger dpi, byte[] imgDpiRostro, byte[] imgDpiFrontal,
			byte[] imgDpiReverso, Admin admin, String correo, java.sql.Date fecha) {
		super(id,nombre, apellido, genero, telefono, usuario, password, estado,correo,fecha);
		this.nit = nit;
		this.dpi = dpi;
		this.imgDpiRostro = imgDpiRostro;
		this.imgDpiFrontal = imgDpiFrontal;
		this.imgDpiReverso = imgDpiReverso;
		this.admin = admin;
	}
	
	public User (int id) {
		super(id);
	}
	
	public User() {}




	public String getNit() {
		return nit;
	}



	public void setNit(String nit) {
		this.nit = nit;
	}



	public BigInteger getDpi() {
		return dpi;
	}



	public void setDpi(BigInteger dpi) {
		this.dpi = dpi;
	}



	public byte[] getImgDpiRostro() {
		return imgDpiRostro;
	}



	public void setImgDpiRostro(byte[] imgDpiRostro) {
		this.imgDpiRostro = imgDpiRostro;
	}



	public byte[] getImgDpiFrontal() {
		return imgDpiFrontal;
	}



	public void setImgDpiFrontal(byte[] imgDpiFrontal) {
		this.imgDpiFrontal = imgDpiFrontal;
	}



	public byte[] getImgDpiReverso() {
		return imgDpiReverso;
	}



	public void setImgDpiReverso(byte[] imgDpiReverso) {
		this.imgDpiReverso = imgDpiReverso;
	}



	public Admin getAdmin() {
		return admin;
	}



	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "User [nit=" + nit + ", dpi=" + dpi + ", imgDpiRostro=" + imgDpiRostro + ", imgDpiFrontal="
				+ imgDpiFrontal + ", imgDpiReverso=" + imgDpiReverso + ", adminId=" + admin.getId() + 
				", id= "+ super.getId() + ", date= "+ super.getFecha() + ", nombre= "+ super.getNombre() + 
				", apellido= "+ super.getApellido() + ", usuario= "+super.getUsuario()+", password= " + super.getPassword() + "]";
	}





	
	





	

	
	

}
