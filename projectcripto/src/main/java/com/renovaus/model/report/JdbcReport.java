package com.renovaus.model.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcReport implements IReport {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<String[]> dataTableAll(String initDate,String finishDate) {
		return jdbcTemplate.query("SELECT orden.id,user.nombre,user.apellido,user.correo,tipo_negocio.descripcion as tipo_negocio, \r\n" + 
				"tipo_pago.descripcion as transferencia,orden.valor_cripto as valor_cripto, tipo_moneda_cripto.descripcion as cripto,\r\n" + 
				"estados_orden.descripcion as orden, valor_fiat,tipo_moneda_fiat.descripcion as moneda,orden.date,\r\n" + 
				"orden.cuenta_bancaria,orden.tipo_cuenta_bancaria,orden.nombre_cuenta_bancaria,comisiones.comision_compra,comisiones.comision_venta\r\n" + 
				"FROM orden\r\n" + 
				"INNER JOIN tipo_pago on orden.id_tipo_pago=tipo_pago.id\r\n" + 
				"INNER JOIN tipo_moneda_cripto on orden.id_moneda_cripto=tipo_moneda_cripto.id\r\n" + 
				"INNER JOIN estados_orden on orden.id_estado_orden=estados_orden.id\r\n" + 
				"INNER JOIN user on orden.id_user=user.id\r\n" + 
				"INNER JOIN tipo_moneda_fiat on orden.id_moneda_fiat=tipo_moneda_fiat.id\r\n" + 
				"INNER JOIN comisiones on orden.comision=orden.comision\r\n" + 
				"INNER JOIN tipo_negocio on orden.id_tipo_negocio=tipo_negocio.id where orden.date BETWEEN  CAST('"+initDate+"' AS DATE) AND CAST('"+finishDate+"' AS DATE) order by id asc ;",				
				(rs, rowum) ->
						new String[] {
								String.valueOf(rs.getInt("id")),
								String.valueOf(rs.getString("nombre")),
								String.valueOf(rs.getString("apellido")),
								String.valueOf(rs.getString("correo")),
								String.valueOf(rs.getString("tipo_negocio")),
								String.valueOf(rs.getString("transferencia")),
								String.valueOf(rs.getBigDecimal("valor_cripto").toPlainString()),
								String.valueOf(rs.getString("cripto")),
								String.valueOf(rs.getString("orden")),
								String.valueOf(rs.getBigDecimal("valor_fiat").toPlainString()),
								String.valueOf(rs.getString("moneda")),
								String.valueOf(rs.getString("date")),
								String.valueOf(rs.getString("cuenta_bancaria")),
								String.valueOf(rs.getString("tipo_cuenta_bancaria")),
								String.valueOf(rs.getString("nombre_cuenta_bancaria")),
								String.valueOf(rs.getBigDecimal("comision_compra").toPlainString()),
								String.valueOf(rs.getBigDecimal("comision_venta").toPlainString())						
						}
				);	
	}

}
