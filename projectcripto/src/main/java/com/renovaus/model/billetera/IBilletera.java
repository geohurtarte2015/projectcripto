package com.renovaus.model.billetera;

import java.util.List;

import com.renovaus.pojo.Billetera;
import com.renovaus.pojo.HistoriaMensaje;
import com.renovaus.pojo.Orden;

public interface IBilletera {
	
	   int save(Billetera billetera);
	
	   List <Billetera> findByUser(int id);
	   
	   List<String[]> findByUserType(int id,int idCrypto);
	
	   List<String[]> dataTableFindByUser(int id);

}
