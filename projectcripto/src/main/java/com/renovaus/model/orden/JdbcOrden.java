package com.renovaus.model.orden;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;


import com.renovaus.pojo.EstadoOrden;
import com.renovaus.pojo.Orden;
import com.renovaus.pojo.Rol;
import com.renovaus.pojo.TipoNegocio;
import com.renovaus.pojo.TipoPago;
import com.renovaus.pojo.User;

@Repository
public class JdbcOrden implements IOrden {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    

	@Override
	public int save(Orden orden) {
		return jdbcTemplate.update("insert into orden "
				+ "(id_tipo_pago,"
				+ " id_tipo_negocio, "
				+ "imagen_voucher, "
				+ "id_estado_orden, "
				+ "valor_fiat, "
				+ "valor_cripto, "	
				+ "id_moneda_fiat, "
				+ "id_moneda_cripto, "
				+ "cuenta_bancaria, "
				+ "tipo_cuenta_bancaria, "
				+ "id_banco, "
				+ "id_user) values (?,?,?,?,?,?,?,?,?,?,?,?)",
				orden.getTipoPago().getId(),
				orden.getTipoNegocio().getId(),
				orden.getImagenVoucher(),
				orden.getEstadoOrden().getId(),
				orden.getFiat(),
				orden.getCripto(),
				orden.getCuentaBancaria(),
				orden.getTipoCuentaBancaria(),
				orden.getBanco(),
				orden.getUser().getId()
				);
	}


	@Override
	public List<Orden> findAll() {		
		return jdbcTemplate.query("select * from orden",
				(rs, rowum) ->
					new Orden(
							rs.getInt("id"),
							new TipoPago(rs.getInt("id_tipo_pago")),
							new TipoNegocio(rs.getInt("id_tipo_negocio")),
							rs.getBlob("imagen_voucher"),						
							(rs.getBigDecimal("valor_fiat")),
							(rs.getBigDecimal("valor_cripto")),
							new User(rs.getInt("id_user")),
							rs.getDate("date"),					
						    new EstadoOrden(rs.getInt("id_estado_orden")),
						    rs.getString("cuenta_bancaria"),
							rs.getString("tipo_cuenta_bancaria"),
							rs.getInt("id_banco"),
							rs.getInt("billetera"))
							);
	}


	@Override
	public List<Orden> findByUser(int id) {
		return jdbcTemplate.query("SELECT * FROM ((orden inner join user on orden.id_user=user.id) inner join admin on user.id_admin=admin.id) where user.id= ?",
				new Object[]{id},
				(rs, rowum) ->
					new Orden(
							rs.getInt("id"),
							new TipoPago(rs.getInt("id_tipo_pago")),
							new TipoNegocio(rs.getInt("id_tipo_negocio")),
							rs.getBlob("imagen_voucher"),						
							(rs.getBigDecimal("valor_fiat")),
							(rs.getBigDecimal("valor_cripto")),
							new User(rs.getInt("id_user")),
							rs.getDate("date"),					
						    new EstadoOrden(rs.getInt("id_estado_orden")),
						    rs.getString("cuenta_bancaria"),
							rs.getString("tipo_cuenta_bancaria"),
							rs.getInt("id_banco"),
							rs.getInt("billtera"))			
							);
	}


	@Override
	public List<Orden> findByOrderByUser(int idUser,int idOrder) {
		return jdbcTemplate.query("select * from orden where id_user=? and id=?",
				new Object[]{idUser,idOrder},				
				(rs, rowum) ->
					new Orden(
							rs.getInt("id"),
							new TipoPago(rs.getInt("id_tipo_pago")),
							new TipoNegocio(rs.getInt("id_tipo_negocio")),
							rs.getBlob("imagen_voucher"),						
							(rs.getBigDecimal("valor_fiat")),
							(rs.getBigDecimal("valor_cripto")),
							new User(rs.getInt("id_user")),
							rs.getDate("date"),					
						    new EstadoOrden(rs.getInt("id_estado_orden")),
						    rs.getString("cuenta_bancaria"),
							rs.getString("tipo_cuenta_bancaria"),
							rs.getInt("id_banco"),
							rs.getInt("billetera"))			
							);
	}


	@Override
	public List<Orden> findByAdmin(int id) {
		return jdbcTemplate.query("SELECT * FROM ((orden inner join user on orden.id_user=user.id) inner join admin on user.id_admin=admin.id) where admin.id=?",
				new Object[]{id},				
				(rs, rowum) ->
					new Orden(
							rs.getInt("id"),
							new TipoPago(rs.getInt("id_tipo_pago")),
							new TipoNegocio(rs.getInt("id_tipo_negocio")),
							rs.getBlob("imagen_voucher"),						
							(rs.getBigDecimal("valor_fiat")),
							(rs.getBigDecimal("valor_cripto")),
							new User(rs.getInt("id_user")),
							rs.getDate("date"),					
						    new EstadoOrden(rs.getInt("id_estado_orden")),
						    rs.getString("cuenta_bancaria"),
							rs.getString("tipo_cuenta_bancaria"),
							rs.getInt("id_banco"),
							rs.getInt("billetera"))				
							);
	}


	@Override
	public int updateEstado(int idOrden , int idEstado) {
	       return jdbcTemplate.update(
	                "update orden set id_estado_orden = ? where id =?",
	                idOrden, idEstado);			    
	}

	
	@Override
	public int deleteById(int id) {
		  return jdbcTemplate.update(
	                "delete from orden where id = ?",
	                id);
	}


	@Override
	public List<String[]> dataTableFindByUserSell(int id) {
		return jdbcTemplate.query("SELECT \r\n" + 
				"orden.id as orden, \r\n" + 
				"estados_orden.descripcion as estado,\r\n" + 
				"orden.valor_fiat as fiat, \r\n" + 
				"tipo_moneda_fiat.descripcion as moneda_fiat, \r\n" + 
				"orden.valor_cripto as recibido_criptomonedas, \r\n" + 
				"tipo_moneda_cripto.descripcion as moneda_cripto, \r\n" + 
				"tipo_pago.descripcion as tipo_pago,\r\n" + 
				"date FROM  orden \r\n" + 
				"inner join tipo_moneda_cripto on orden.id_moneda_cripto=tipo_moneda_cripto.id\r\n" + 
				"inner join tipo_moneda_fiat on orden.id_moneda_fiat=tipo_moneda_fiat.id\r\n" + 
				"inner join tipo_pago on orden.id_tipo_pago=tipo_pago.id\r\n" + 
				"inner join estados_orden on orden.id_estado_orden=estados_orden.id\r\n" + 
				"where id_user=? and id_tipo_negocio=1",
				new Object[]{id},	
				(rs, rowum) ->
						new String[] {
								String.valueOf(rs.getInt("orden")),
								String.valueOf(rs.getString("estado")),
								String.valueOf(rs.getBigDecimal("fiat").toString()),
								String.valueOf(rs.getString("moneda_fiat")),						
								String.valueOf(rs.getBigDecimal("recibido_criptomonedas").toString()),
								String.valueOf(rs.getString("moneda_cripto")),
								String.valueOf(rs.getString("tipo_pago")),
								String.valueOf(rs.getString("date"))
								
						}
				);		
	}


	@Override
	public List<String[]> dataTableFindByUserBuy(int id) {
		return jdbcTemplate.query("SELECT \r\n" + 
				"orden.id as orden, \r\n" + 
				"estados_orden.descripcion as estado,\r\n" + 
				"orden.valor_fiat as recibido_fiat, \r\n" + 
				"tipo_moneda_fiat.descripcion as moneda_fiat, \r\n" + 
				"orden.valor_cripto as recibido_criptomonedas, \r\n" + 
				"tipo_moneda_cripto.descripcion as moneda_cripto, \r\n" + 
				"orden.cuenta_bancaria as cuenta_bancaria,\r\n" + 
				"orden.tipo_cuenta_bancaria as tipo_cuenta_bancaria,\r\n" + 
				"date FROM  orden \r\n" + 
				"inner join tipo_moneda_cripto on orden.id_moneda_cripto=tipo_moneda_cripto.id\r\n" + 
				"inner join tipo_moneda_fiat on orden.id_moneda_fiat=tipo_moneda_fiat.id\r\n" + 
				"inner join estados_orden on orden.id_estado_orden=estados_orden.id\r\n" + 
				"where id_user=? and id_tipo_negocio=2",
				new Object[]{id},	
				(rs, rowum) ->
						new String[] {
								String.valueOf(rs.getInt("orden")),
								String.valueOf(rs.getString("estado")),
								String.valueOf(rs.getBigDecimal("recibido_fiat").toString()),
								String.valueOf(rs.getString("moneda_fiat")),
								String.valueOf(rs.getBigDecimal("recibido_criptomonedas").toString()),
								String.valueOf(rs.getString("moneda_cripto")),
								String.valueOf(rs.getString("cuenta_bancaria")),
								String.valueOf(rs.getString("tipo_cuenta_bancaria")),							
								String.valueOf(rs.getString("date"))
						}
				);	
		
	}

}
