package com.renovaus.model.report;

import java.math.BigDecimal;
import java.util.List;


import com.renovaus.pojo.Orden;
import com.renovaus.pojo.Rol;

public interface IReport {	  
	   
	   //para uso de la vista de dataTable * for use DataTable view *
	   List<String[]> dataTableAll(String initDate,String finishDate);
	   

}