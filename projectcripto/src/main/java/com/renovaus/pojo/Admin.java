package com.renovaus.pojo;

import java.sql.Blob;
import java.sql.Date;


public class Admin extends Persona {
	
	private Blob imagenRostro;
	private Rol rol;
	
	public Admin(int id) {
		super(id);
	}
	
	public Admin(String nombre, String apellido, String genero, int telefono, String usuario,
			String password, Estado estado, Blob imagenRostro, Rol rol,String correo) {
		
		super(nombre, apellido, genero, telefono, usuario, password, estado,correo);
		this.imagenRostro = imagenRostro;
		this.rol = rol;
	}
	
	public Admin(int id,String nombre, String apellido, String genero, int telefono, String usuario,
			String password, Estado estado, Blob imagenRostro, Rol rol,String correo,Date fecha) {
		
		super(id,nombre, apellido, genero, telefono, usuario, password, estado,correo,fecha);
		this.imagenRostro = imagenRostro;
		this.rol = rol;
	}
	
	

	
	
	public Blob getImagenRostro() {
		return imagenRostro;
	}
	public void setImagenRostro(Blob imagenRostro) {
		this.imagenRostro = imagenRostro;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}

	
	

}
