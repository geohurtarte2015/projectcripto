package com.renovaus.model.rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.renovaus.pojo.Rol;

@Repository
public class JdbcRol implements IRol {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    


	@Override
	public int save(Rol rol) {
		return jdbcTemplate.update("insert into roles (id,descripcion) values (?,?)",rol.getId(),rol.getDescription());
	}

}
