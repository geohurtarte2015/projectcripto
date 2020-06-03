package com.renovaus.pojo;

import java.math.BigInteger;
import java.sql.Blob;
import java.util.Date;

public class Persona {
	
	private int id;
	private String nombre;
	private String apellido;
	private String genero;
	private int telefono;
	private String usuario;
	private String password;
	private Estado estado;
	private String correo;
	private java.sql.Date fecha;
	
	public Persona() {}
	
	public java.sql.Date getFecha() {
		return fecha;
	}


	public void setFecha(java.sql.Date fecha) {
		this.fecha = fecha;
	}


	public Persona(int id) {
		this.id = id;
	}
	

	public Persona( String nombre, String apellido, String genero, int telefono, String usuario, String password,
			Estado estado, String correo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.genero = genero;
		this.telefono = telefono;
		this.usuario = usuario;
		this.password = password;
		this.estado = estado;
		this.correo = correo;
	}
	
	public Persona(int id, String nombre, String apellido, String genero, int telefono, String usuario, String password,
			Estado estado, String correo, java.sql.Date fecha) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.genero = genero;
		this.telefono = telefono;
		this.usuario = usuario;
		this.password = password;
		this.estado = estado;
		this.correo = correo;
		this.fecha = fecha;
	}

	
	

	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}



	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}




	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", genero=" + genero
				+ ", telefono=" + telefono + ", usuario=" + usuario + ", password=" + password + ", estado=" + estado
				+ ", correo=" + correo + "]";
	}
	
	



}
