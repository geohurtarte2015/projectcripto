package com.renovaus.bancos;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.renovaus.pojo.Admin;
import com.renovaus.pojo.Banco;
import com.renovaus.pojo.Estado;
import com.renovaus.pojo.User;

@Repository
public class JdbcBanco implements IBanco{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<Banco> findAll() {

		return jdbcTemplate.query("select * from banco",
				(rs, rowum) ->
						new Banco(
								rs.getInt("id"),
								rs.getString("descripcion"),
								rs.getInt("id_moneda_fiat"))
				);
	}
	
	

}
