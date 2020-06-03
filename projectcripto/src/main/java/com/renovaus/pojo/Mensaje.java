package com.renovaus.pojo;

import java.sql.Blob;

public class Mensaje {

	private int id;
	private Blob imagen;
	private String mensaje;

	
	
	public Mensaje(Blob imagen, String mensaje) {
		super();	
		this.imagen = imagen;
		this.mensaje = mensaje;
	}
	
	public Mensaje(int id, Blob imagen, String mensaje) {
		super();	
		this.imagen = imagen;
		this.mensaje = mensaje;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Blob getImagen() {
		return imagen;
	}
	public void setImagen(Blob imagen) {
		this.imagen = imagen;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", imagen=" + imagen + ", mensaje=" + mensaje + "]";
	}

}
