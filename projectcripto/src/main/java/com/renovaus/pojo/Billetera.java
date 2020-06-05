package com.renovaus.pojo;

public class Billetera {
	
	private int id;	
	private String direccion;
	private int usuario;
	private String descripcion;
	
	public Billetera(String direccion,String descripcion, int idUser) {
		
		this.descripcion = descripcion;
		this.usuario = idUser;
		this.direccion = direccion;
	}
	
	public Billetera(int id ,String direccion,String descripcion, int idUser) {
		this.id = id;
		this.descripcion = descripcion;
		this.usuario = idUser;
		this.direccion = direccion;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

}
