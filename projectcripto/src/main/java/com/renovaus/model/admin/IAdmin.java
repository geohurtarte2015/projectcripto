package com.renovaus.model.admin;


import com.renovaus.pojo.Admin;
import com.renovaus.pojo.User;

public interface IAdmin {
	
	  int save(Admin admin);
	  Admin findByAdmin(String user, String password);

}
