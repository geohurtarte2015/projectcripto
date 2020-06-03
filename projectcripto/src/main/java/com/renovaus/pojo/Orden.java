package com.renovaus.pojo;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Date;

public class Orden {
	
	private int id;
	private TipoPago tipoPago;
	private TipoNegocio tipoNegocio;
	private Blob imagenVoucher;	
	private BigDecimal fiat;
	private BigDecimal cripto;
	private User user;
	private EstadoOrden estadoOrden;
	private Date fecha;
	private String tipoCuentaBancaria;
	private String cuentaBancaria;
	private int nombreBanco;
	
	


	
	public Orden(TipoPago tipoPago, TipoNegocio tipoNegocio, Blob imagenVoucher, BigDecimal fiat,
			BigDecimal cripto, User user, EstadoOrden estadoOrden,String tipoCuentaBancaria, String cuentaBancaria, int nombreBanco) {
		super();
		this.tipoPago = tipoPago;
		this.tipoNegocio = tipoNegocio;
		this.imagenVoucher = imagenVoucher;
		this.fiat = fiat;
		this.cripto = cripto;
		this.user = user;
		this.estadoOrden = estadoOrden;
		this.tipoCuentaBancaria = tipoCuentaBancaria;
		this.cuentaBancaria = cuentaBancaria;
		this.nombreBanco =  nombreBanco;
		
	}
	
	public Orden(int id,TipoPago tipoPago, TipoNegocio tipoNegocio, Blob imagenVoucher, BigDecimal fiat,
			BigDecimal cripto, User user, Date fecha, EstadoOrden estadoOrden,String tipoCuentaBancaria, String cuentaBancaria, int nombreBanco) {
		super();
		this.id = id;
		this.tipoPago = tipoPago;
		this.tipoNegocio = tipoNegocio;
		this.imagenVoucher = imagenVoucher;
		this.fiat = fiat;
		this.cripto = cripto;
		this.user = user;
		this.estadoOrden = estadoOrden;
		this.fecha = fecha;
		this.tipoCuentaBancaria = tipoCuentaBancaria;
		this.cuentaBancaria = cuentaBancaria;
		this.nombreBanco =  nombreBanco;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TipoPago getTipoPago() {
		return tipoPago;
	}
	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}
	public TipoNegocio getTipoNegocio() {
		return tipoNegocio;
	}
	public void setTipoNegocio(TipoNegocio tipoNegocio) {
		this.tipoNegocio = tipoNegocio;
	}
	public Blob getImagenVoucher() {
		return imagenVoucher;
	}
	public void setImagenVoucher(Blob imagenVoucher) {
		this.imagenVoucher = imagenVoucher;
	}
	public BigDecimal getFiat() {
		return fiat;
	}
	public void setFiat(BigDecimal fiat) {
		this.fiat = fiat;
	}
	public BigDecimal getCripto() {
		return cripto;
	}
	public void setCripto(BigDecimal cripto) {
		this.cripto = cripto;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public EstadoOrden getEstadoOrden() {
		return estadoOrden;
	}
	public void setEstadoOrden(EstadoOrden estadoOrden) {
		this.estadoOrden = estadoOrden;
	}
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	

	
	public String getTipoCuentaBancaria() {
		return tipoCuentaBancaria;
	}


	public void setTipoCuentaBancaria(String tipoCuentaBancaria) {
		this.tipoCuentaBancaria = tipoCuentaBancaria;
	}





	public int getBanco() {
		return nombreBanco;
	}


	public void setBanco(int banco) {
		this.nombreBanco = banco;
	}



	@Override
	public String toString() {
		return "Orden [id=" + id + ", tipoPago=" + tipoPago + ", tipoNegocio=" + tipoNegocio + ", imagenVoucher="
				+ imagenVoucher + ", fiat=" + fiat + ", cripto=" + cripto + "]";
	}
	

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}


	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}


	public int getNombreBanco() {
		return nombreBanco;
	}


	public void setNombreBanco(int nombreBanco) {
		this.nombreBanco = nombreBanco;
	}
	

	public Orden(int id) {
		
		this.id = id;
	}
	


	
	

}
