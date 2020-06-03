package com.renovaus.utils.datatable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetJson {
	
	   public String getJsonDataTable(final List<String[]> list) {
	        final DataTableObject dataTableObject = new DataTableObject();
	        final List<Object> objectProducts = new ArrayList<Object>();
	        for (final String[] mainPack : list) {
	            final int size = mainPack.length;
	            final List<Object> listObject = new ArrayList<Object>();
	            for (int x = 0; x < size; ++x) {
	                listObject.add(mainPack[x]);
	            }
	            objectProducts.add(listObject);
	        }
	        dataTableObject.setAaData((List)objectProducts);
	        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        final String json = gson.toJson((Object)dataTableObject);
	        return json;
	    }

}
