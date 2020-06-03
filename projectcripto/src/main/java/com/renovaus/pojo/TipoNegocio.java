package com.renovaus.pojo;

public class TipoNegocio {
	

	private int id;
	private String descripcion;
	
	public TipoNegocio(String descripcion) {
		super();	
		this.descripcion = descripcion;
	}
	
	public TipoNegocio(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "TipoNegocio [id=" + id + ", descripcion=" + descripcion + "]";
	}


}
