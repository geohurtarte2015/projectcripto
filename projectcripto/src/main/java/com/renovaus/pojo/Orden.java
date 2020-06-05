package com.renovaus.pojo;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Date;

public class Orden {
	
	private int id;
	private TipoPago tipoPago;
	private TipoNegocio tipoNegocio;
	private byte[] imagenVoucher;
	private BigDecimal fiat;
	private BigDecimal cripto;
	private User user;
	private EstadoOrden estadoOrden;
	private Date fecha;
	private String tipoCuentaBancaria;
	private String cuentaBancaria;
	private int nombreBanco;
	private int billetera;
	private int monedaFiat;
	private int monedaCripto;
	
	
	
	
	
	
	public Orden(TipoNegocio tipoNegocio, BigDecimal fiat,
			BigDecimal cripto, User user, EstadoOrden estadoOrden,String tipoCuentaBancaria, 
			String cuentaBancaria, int nombreBanco, int monedaFiat, int monedaCripto) {
		super();	
		this.tipoNegocio = tipoNegocio;	
		this.fiat = fiat;
		this.cripto = cripto;
		this.user = user;
		this.estadoOrden = estadoOrden;
		this.tipoCuentaBancaria = tipoCuentaBancaria;
		this.cuentaBancaria = cuentaBancaria;
		this.nombreBanco =  nombreBanco;	
		this.monedaFiat = monedaFiat;
		this.monedaCripto = monedaCripto;			
	}
	


	


	public Orden(TipoPago tipoPago, TipoNegocio tipoNegocio, byte[] imagenVoucher, BigDecimal fiat,
			BigDecimal cripto, User user, EstadoOrden estadoOrden,String tipoCuentaBancaria, 
			String cuentaBancaria, int nombreBanco, int billetera, int monedaFiat, int monedaCripto) {
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
		this.billetera = billetera;
		this.monedaFiat = monedaFiat;
		this.monedaCripto = monedaCripto;	

		
	}
	
	
	
	public Orden(TipoPago tipoPago, TipoNegocio tipoNegocio, byte[] imagenVoucher, BigDecimal fiat,
			BigDecimal cripto, User user, EstadoOrden estadoOrden,String tipoCuentaBancaria, 
			String cuentaBancaria, int billetera, int monedaFiat, int monedaCripto) {
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
		this.billetera = billetera;
		this.monedaFiat = monedaFiat;
		this.monedaCripto = monedaCripto;	

		
	}
	
	public Orden(int id,TipoPago tipoPago, TipoNegocio tipoNegocio, byte[] imagenVoucher, BigDecimal fiat,
			BigDecimal cripto, User user, Date fecha, EstadoOrden estadoOrden,String tipoCuentaBancaria, 
			String cuentaBancaria, int nombreBanco, int billetera) {
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
		this.billetera = billetera;
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
	
	public int getBilletera() {
		return billetera;
	}

	public void setBilletera(int billetera) {
		this.billetera = billetera;
	}

	public int getMonedaFiat() {
		return monedaFiat;
	}

	public void setMonedaFiat(int monedaFiat) {
		this.monedaFiat = monedaFiat;
	}

	public int getMonedaCripto() {
		return monedaCripto;
	}

	public void setMonedaCripto(int monedaCripto) {
		this.monedaCripto = monedaCripto;
	}
	
	public byte[] getImagenVoucher() {
		return imagenVoucher;
	}

	public void setImagenVoucher(byte[] imagenVoucher) {
		this.imagenVoucher = imagenVoucher;
	}

	
	

}
