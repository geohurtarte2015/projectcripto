package com.renovaus.pojo;

public class MonedaCripto {
	
	private int id;
	private String descripcion;
	
	
	public MonedaCripto(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}
