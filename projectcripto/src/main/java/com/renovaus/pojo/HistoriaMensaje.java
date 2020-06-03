package com.renovaus.pojo;

public class HistoriaMensaje {
	
	private int id;
	private Orden orden;
	private Mensaje mensaje;
			
	
	public HistoriaMensaje(Orden orden, Mensaje mensaje) {
		super();	
		this.orden = orden;
		this.mensaje = mensaje;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Orden getOrden() {
		return orden;
	}
	public void setOrden(Orden orden) {
		this.orden = orden;
	}
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}


	@Override
	public String toString() {
		return "HistoriaMensaje [id=" + id + ", orden=" + orden + ", mensaje=" + mensaje + "]";
	}
	
	

}
