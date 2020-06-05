package com.renovaus.pojo;

public class Banco {
	
	private int id;
	private String descripcion;
	private int moneda;
	
	
	public Banco(int id, String descripcion, int moneda) {
		this.id = id;
		this.descripcion = descripcion;
		this.moneda = moneda;		
	}
	
	public Banco(String descripcion, int moneda) {		
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
	public int getMoneda() {
		return moneda;
	}
	public void setMoneda(int moneda) {
		this.moneda = moneda;
	}



}
