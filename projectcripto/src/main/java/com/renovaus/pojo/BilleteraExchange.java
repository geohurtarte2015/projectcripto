package com.renovaus.pojo;

public class BilleteraExchange {
	
	private int id;
	private String descripcion;
	private String direccion;
	private int tipoCripto;
	
	
	public BilleteraExchange(String descripcion,int tipoCripto, String direccion) {
		
		this.descripcion = descripcion;
		this.tipoCripto = tipoCripto;
		this.direccion = direccion;
	}
	
	
	
	public BilleteraExchange(int id ,String descripcion,int tipoCripto, String direccion) {
		this.id = id;
		this.descripcion = descripcion;
		this.tipoCripto = tipoCripto;
		this.direccion = direccion;
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getTipoCripto() {
		return tipoCripto;
	}
	public void setTipoCripto(int tipoCripto) {
		this.tipoCripto = tipoCripto;
	}

	
	

}
