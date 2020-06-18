package com.renovaus.model.user;


import java.util.List;

import com.renovaus.pojo.User;

public interface IUser {
	
	  int save(User user);
	  
	  List<User> findAll();
	  
	  User findByUser(int id);
	  
	  int updateUser(int idUser);
	  
	  int updateEstado(int idUser, int idEstado);
	  
	  int updateUserNameAndPassword(String username,String password,int idUser);
	  
	  User findByUser(String user, String password);
	  
	  //para uso de la vista de dataTable * for use DataTable view *
	  List<String[]> findAllTable();

}
