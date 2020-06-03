package com.renovaus.model.mensaje;

import java.util.List;

import com.renovaus.pojo.Mensaje;
import com.renovaus.pojo.Rol;

public interface IMensaje {
	
	   int save(Mensaje mensaje);
	   
	   List<Mensaje> findByOrden(int id);
	   
	

}
