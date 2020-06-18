package com.renovaus.model.user;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.renovaus.pojo.Admin;
import com.renovaus.pojo.Estado;
import com.renovaus.pojo.EstadoOrden;
import com.renovaus.pojo.Orden;
import com.renovaus.pojo.Rol;
import com.renovaus.pojo.TipoNegocio;
import com.renovaus.pojo.TipoPago;
import com.renovaus.pojo.User;

@Repository
public class JdbcUser implements IUser{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public int save(User user) {
		return jdbcTemplate.update("insert into user ("
				+ "nombre, "
				+ "apellido, "
				+ "nit, "
				+ "dpi, "
				+ "genero, "
				+ "telefono, "
				+ "id_admin, "
				+ "id_estado, "
				+ "img_dpi_rostro, "
				+ "img_dpi_frontal, "
				+ "img_dpi_reverso, "
				+ "usuario, "
				+  "correo, "
				+ "password) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
			   ,user.getNombre(),
			   user.getApellido(),
			   user.getNit(),
			   user.getDpi(),
			   user.getGenero(),
			   user.getTelefono(),
			   user.getAdmin().getId(),
			   user.getEstado().getId(),
			   user.getImgDpiRostro(),
			   user.getImgDpiFrontal(),
			   user.getImgDpiReverso(),
			   user.getUsuario(),
			   user.getCorreo(),
			   user.getPassword());	
	}

	@Override
	public List<User> findAll() {	
		
		return jdbcTemplate.query("select * from user",
				(rs, rowum) ->
						new User(
								rs.getInt("id"),
								rs.getString("nombre"),
								rs.getString("apellido"),
								rs.getString("genero"),
								rs.getInt("telefono"),
								rs.getString("usuario"),
								rs.getString("password"),
								new Estado(rs.getInt("id_estado")),
								rs.getString("nit"),
								BigInteger.valueOf(rs.getLong("dpi")),
								rs.getBytes("img_dpi_rostro"),
								rs.getBytes("img_dpi_frontal"),
								rs.getBytes("img_dpi_reverso"),
								new Admin(rs.getInt("id_admin")),
								rs.getString("correo"),
								rs.getDate("date"))
				);
	}
	
	public List<String[]> findAllTable(){
		
		return jdbcTemplate.query("SELECT user.id,user.Nombre,user.Apellido,estados.descripcion, user.date  from user inner join estados on user.id_estado=estados.id where user.id_estado=2",
				(rs, rowum) ->
						new String[] {
								String.valueOf(rs.getInt("id")),
								String.valueOf(rs.getString("nombre")),
								String.valueOf(rs.getString("apellido")),						
								String.valueOf(rs.getString("descripcion")),							
								String.valueOf(rs.getDate("date"))}
				);
		
	}
	
	@Override
	public User findByUser(int id) {
		User user = jdbcTemplate.queryForObject("Select * from user where id= ?",
				new Object[]{id},
				(rs, rowum) ->
					new User(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("apellido"),
						rs.getString("genero"),
						rs.getInt("telefono"),
						rs.getString("usuario"),
						rs.getString("password"),
						new Estado(rs.getInt("id_estado")),
						rs.getString("nit"),
						BigInteger.valueOf(rs.getLong("dpi")),
						rs.getBytes("img_dpi_rostro"),
						rs.getBytes("img_dpi_frontal"),
						rs.getBytes("img_dpi_reverso"),
						new Admin(rs.getInt("id_admin")),
						rs.getString("correo"),
						rs.getDate("date"))
				);
		return user; 
	}

	@Override
	public User findByUser(String userName, String password) {
		User user = null;
		try {
		 user = jdbcTemplate.queryForObject("Select * from user where usuario=? and password= ?",
				new Object[]{userName,password},
				(rs, rowum) ->
					new User(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("apellido"),
						rs.getString("genero"),
						rs.getInt("telefono"),
						rs.getString("usuario"),
						rs.getString("password"),
						new Estado(rs.getInt("id_estado")),
						rs.getString("nit"),
						BigInteger.valueOf(rs.getLong("dpi")),
						rs.getBytes("img_dpi_rostro"),
						rs.getBytes("img_dpi_frontal"),
						rs.getBytes("img_dpi_reverso"),
						new Admin(rs.getInt("id_admin")),
						rs.getString("correo"),
						rs.getDate("date"))
				);
		
		return user; 
		}catch(DataAccessException  e) 
		{
			System.out.println("Error al guardar");
			System.out.println(e);
			return user;
			
			
		}
	}

	public int updateUser(int idUser) {
	       return jdbcTemplate.update(
	                "update user set id_estado = 1 where id =?",
	                idUser);			    
	}


	public int updateUserNameAndPassword(String username, String password, int idUser) {
		return jdbcTemplate.update(
                "update user set usuario = ?,password = ? where id =?",
                username,password,idUser);
	}

	@Override
	public int updateEstado(int idUser, int idEstado) {
	    return jdbcTemplate.update(
                "update usuario set id_estado = ? where id =?",
                idEstado, idUser);	
	}


}
