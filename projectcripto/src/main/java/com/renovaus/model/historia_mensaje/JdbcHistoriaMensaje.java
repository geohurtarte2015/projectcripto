package com.renovaus.model.historia_mensaje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.renovaus.pojo.HistoriaMensaje;
import com.renovaus.pojo.Mensaje;
import com.renovaus.pojo.Rol;

@Repository
public class JdbcHistoriaMensaje implements IHistoriaMensaje {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;    


	@Override
	public int save(HistoriaMensaje historiaMensaje) {
		 return jdbcTemplate.update("insert into values (id_orden,id_mensaje) values (?,?)",
				historiaMensaje.getOrden().getId(),
				historiaMensaje.getMensaje().getId());
	}

}
