package com.renovaus.pojo;

public class Comision {
	
	private double  comisionVenta;
	private double   comisionCompra;
	
	public Comision(double comisionVenta,double comisionCompra) {
		this.comisionVenta=comisionVenta;
		this.comisionCompra=comisionCompra;
	}
	
	public double getComisionVenta() {
		return comisionVenta;
	}
	public void setComisionVenta(double comisionVenta) {
		this.comisionVenta = comisionVenta;
	}
	public double getComisionCompra() {
		return comisionCompra;
	}
	public void setComisionCompra(double comisionCompra) {
		this.comisionCompra = comisionCompra;
	}

	
	

}
