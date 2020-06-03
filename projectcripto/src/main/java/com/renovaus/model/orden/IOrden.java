package com.renovaus.model.orden;

import java.util.List;


import com.renovaus.pojo.Orden;
import com.renovaus.pojo.Rol;

public interface IOrden {
	
	   int save(Orden orden);
	   int updateEstado(int idEstado, int idOrden);
	   int deleteById(int id);
	   List <Orden> findAll();
	   List <Orden> findByUser(int id);
	   List <Orden> findByOrderByUser(int idUser,int idOrder);
	   List <Orden> findByAdmin(int id);
	   
	   
	   //para uso de la vista de dataTable * for use DataTable view *
	   List<String[]> dataTableFindByUserSell(int id);
	   
	   //para uso de la vista de dataTable * for use DataTable view *
	   List<String[]> dataTableFindByUserBuy(int id);

}
