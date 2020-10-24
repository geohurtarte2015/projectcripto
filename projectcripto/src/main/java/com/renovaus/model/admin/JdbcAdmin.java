package com.renovaus.model.admin;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.renovaus.pojo.Admin;
import com.renovaus.pojo.Estado;
import com.renovaus.pojo.Rol;
import com.renovaus.pojo.User;
import com.renovaus.pojo.Admin;

@Repository
public class JdbcAdmin implements IAdmin{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public int save(Admin admin) {
		return jdbcTemplate.update("insert into admin ("
				+ "nombre, "
				+ "apellido, "
				+ "correo, "
				+ "telefono, "
				+ "usuario, "
				+ "password, "
				+ "imagen, "
				+ "genero, "
				+ "id_rol, "
				+ "id_estado) values (?,?,?,?,?,?,?,?,?,?)" 		
			   ,admin.getNombre(),
			   admin.getApellido(),
			   admin.getCorreo(),
			   admin.getTelefono(),
			   admin.getUsuario(),
			   admin.getPassword(),
			   admin.getImagenRostro(),
			   admin.getGenero(),
			   admin.getRol().getId(),
			   admin.getEstado().getId());	
	}

	
	
	@Override
	public Admin findByAdmin(String user, String password) {
		Admin admin = null;
		try {
		 admin = jdbcTemplate.queryForObject("Select * from admin where usuario=? and password= ?",
				new Object[]{user,password},
				(rs, rowum) ->
					new Admin(	
						rs.getInt("id"),	
						rs.getString("nombre"),
						rs.getString("apellido"),
						rs.getString("genero"),
						rs.getInt("telefono"),
						rs.getString("usuario"),
						rs.getString("password"),
						new Estado(rs.getInt("id_estado")),						
						new Rol(rs.getInt("id_rol")),						
						rs.getString("correo"))
				);
		
		return admin; 
		}catch(DataAccessException  e) 
		{
			System.out.println("Error al guardar");
			System.out.println(e);
			return admin;
			
			
		}
	}
    

}
