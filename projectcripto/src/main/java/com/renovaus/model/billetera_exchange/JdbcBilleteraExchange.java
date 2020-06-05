package com.renovaus.model.billetera_exchange;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.renovaus.pojo.Admin;
import com.renovaus.pojo.BilleteraExchange;
import com.renovaus.pojo.Estado;
import com.renovaus.pojo.User;

@Repository
public class JdbcBilleteraExchange implements IBilleteraExchange {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public BilleteraExchange findByBilleteraExchange(int id) {
		BilleteraExchange billeteraExchange = jdbcTemplate.queryForObject("Select * from billetera_exchange where id= ?",
				new Object[]{id},
				(rs, rowum) ->
					new BilleteraExchange(
						rs.getInt("id"),
						rs.getString("descripcion"),
						rs.getInt("id_tipo_cripto"),
						rs.getString("direccion"))	
				);
		return billeteraExchange; 
	}

}
