package com.renovaus.model.orden;

import java.math.BigDecimal;
import java.util.List;


import com.renovaus.pojo.Orden;
import com.renovaus.pojo.Rol;

public interface IOrden {
	
	   int saveBuy(Orden orden);
	   int saveSell(Orden orden);
	   
	   int updateEstado(int idEstado, int idOrden);
	   int updateFiat(BigDecimal valorFiat, int idOrden);
	   int updateCripto(BigDecimal valorCripto, int idOrden);
	   int deleteById(int id);
	   List <Orden> findAll();
	   List <Orden> findByUser(int id);
	   List <Orden> findByOrderByUser(int idUser,int idOrder);
	   List <Orden> findByAdmin(int id);
	   Orden findByOrder(int id);
	   Orden findByOrderSell(int id);
	   Orden findByOrderBuy(int id);
	   
	   
	   
	   //para uso de la vista de dataTable * for use DataTable view *
	   List<String[]> dataTableFindByUserSell(int id);
	   
	   
	   //para uso de la vista de dataTable * for use DataTable view *
	   List<String[]> dataTableFindByUserBuy(int id);
	   
	   
	   //para uso de la vista de dataTable * for use DataTable view *
	   List<String[]> dataTableFindByUserSellAdmin();
	   
	   
	   //para uso de la vista de dataTable * for use DataTable view *
	   List<String[]> dataTableFindByUserBuyAdmin();

}
