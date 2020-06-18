package com.renovaus.pojo;

public class MonedaFiat {
	
	private int id;
	private String descripcion;
	
	
	public MonedaFiat(String descripcion) {
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
