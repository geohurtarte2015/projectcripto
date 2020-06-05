package com.renovaus.model.billetera;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.renovaus.pojo.Billetera;
import com.renovaus.pojo.EstadoOrden;
import com.renovaus.pojo.HistoriaMensaje;
import com.renovaus.pojo.Orden;
import com.renovaus.pojo.TipoNegocio;
import com.renovaus.pojo.TipoPago;
import com.renovaus.pojo.User;

@Repository
public class JdbcBilletera implements IBilletera {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<String[]> dataTableFindByUser(int id) {
		return jdbcTemplate.query("SELECT * FROM  billetera where id_usuario=?",
				new Object[]{id},	
				(rs, rowum) ->
						new String[] {
								String.valueOf(rs.getString("direccion")),
								String.valueOf(rs.getString("descripcion")),
								String.valueOf(rs.getDate("fecha_alta").toString())
						}
				);	
	}

	@Override
	public List<Billetera> findByUser(int id) {
		return jdbcTemplate.query("SELECT * FROM  billetera where id_usuario=?",
				new Object[]{id},
				(rs, rowum) ->
					new Billetera(
							rs.getInt("id"),
							rs.getString("direccion"),
							rs.getString("descripcion"),			
							rs.getInt("id_usuario"))			
							);
	}

	@Override	
	public int save(Billetera billetera) {
		 return jdbcTemplate.update("insert into billetera (direccion,id_usuario,descripcion) values (?,?,?)",
				billetera.getDireccion(),
				billetera.getUsuario(),
				billetera.getDescripcion());
	}

}
