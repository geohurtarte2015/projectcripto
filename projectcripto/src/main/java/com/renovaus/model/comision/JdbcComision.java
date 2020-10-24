package com.renovaus.model.comision;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.renovaus.pojo.Admin;
import com.renovaus.pojo.Comision;
import com.renovaus.pojo.Estado;
import com.renovaus.pojo.HistoriaMensaje;
import com.renovaus.pojo.User;

@Repository
public class JdbcComision implements IComision{
	
	
	   @Autowired
	    private JdbcTemplate jdbcTemplate;    
	   
		@Override
		public int save(Comision comision) {
			 return jdbcTemplate.update("insert into comisiones (comision_venta,comision_compra) values (?,?)",
					comision.getComisionVenta(),
					comision.getComisionCompra());
		}

		@Override
		public Comision findById(int id) {
			Comision comision = jdbcTemplate.queryForObject("Select * from comisiones where id= ?",
					new Object[]{id},
					(rs, rowum) ->
						new Comision(							
							rs.getDouble("comision_venta"),
							rs.getDouble("comision_compra")
						)
					);
			return comision; 
		}


	   
	   


}
