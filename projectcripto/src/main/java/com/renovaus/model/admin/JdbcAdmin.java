package com.renovaus.model.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.renovaus.pojo.Admin;
import com.renovaus.pojo.Rol;
import com.renovaus.pojo.User;

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
    

}
