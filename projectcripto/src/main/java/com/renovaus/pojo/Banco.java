package com.renovaus.pojo;

public class Banco {
	
	private int id;
	private String descripcion;
	private String moneda;
	
	
	public Banco(int id, String descripcion, String moneda) {
		this.id = id;
		this.descripcion = descripcion;
		this.moneda = moneda;		
	}
	
	public Banco(String descripcion, String moneda) {		
		this.descripcion = descripcion;
		this.moneda = moneda;		
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
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}



}
