package com.renovaus.model.mensaje;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.renovaus.pojo.EstadoOrden;
import com.renovaus.pojo.Mensaje;
import com.renovaus.pojo.Orden;
import com.renovaus.pojo.Rol;
import com.renovaus.pojo.TipoNegocio;
import com.renovaus.pojo.TipoPago;
import com.renovaus.pojo.User;

@Repository
public class JdbcMensaje implements IMensaje {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;    


	@Override
	public int save(Mensaje mensaje) {
		return jdbcTemplate.update("insert into values (mensaje,imagen) values (?,?)",
		mensaje.getImagen(),
		mensaje.getImagen());
	}


	@Override
	public List<Mensaje> findByOrden(int id) {
		return jdbcTemplate.query("SELECT  historia_mjs.id_mensaje,mensaje.mensaje,mensaje.imagen FROM historia_mjs inner join mensaje on mensaje.id=historia_mjs.id_mensaje where historia_mjs.id_orden= ?",
				new Object[]{id},
				(rs, rowum) ->
					new Mensaje(
							rs.getInt("id"),
							rs.getBlob("imagen"),										
						    rs.getString("mensaje"))			
							);
	}
	

}
