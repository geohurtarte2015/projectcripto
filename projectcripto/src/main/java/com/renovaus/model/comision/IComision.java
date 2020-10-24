package com.renovaus.model.comision;

import com.renovaus.pojo.Comision;
import com.renovaus.pojo.HistoriaMensaje;
import com.renovaus.pojo.User;

public interface IComision {
	
	   int save(Comision comision);
	   Comision findById(int id);


}
