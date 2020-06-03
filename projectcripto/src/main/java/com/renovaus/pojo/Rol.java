package com.renovaus.pojo;

public class Rol {
	
	
	
	public Rol(String description) {	
		this.description = description;
	}
	
	public Rol(int id) {
		this.id = id;
	}
	
	
	


	private int id;
	private String description;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Override
	public String toString() {
		return "Rol [id=" + id + ", description=" + description + "]";
	}

	

}
