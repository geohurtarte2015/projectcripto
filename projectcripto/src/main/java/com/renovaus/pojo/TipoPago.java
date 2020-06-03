package com.renovaus.pojo;

public class TipoPago {
	
	private int id;
	private String descripcion;
	
	public TipoPago(int id) {
		this.id = id;
	}
	
	
	@Override
	public String toString() {
		return "TipoPago [id=" + id + ", descripcion=" + descripcion + "]";
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
	public TipoPago(String descripcion) {
		super();
		this.descripcion = descripcion;
	}


}
