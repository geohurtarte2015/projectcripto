package com.renovaus.pojo;

public class Estado {
	
	private int id;
	private String description;
	
	
	public Estado(String description) {
	
		this.description = description;
	}
	
	public Estado(int id) {
		
		this.id = id;
	}
	
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
		return "Estado [id=" + id + ", description=" + description + "]";
	}



	

}
