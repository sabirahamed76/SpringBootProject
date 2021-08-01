package com.home.sabir.java.springboot.dto;

import javax.persistence.Column;

public class CompanyDto {
	
	
    private long id;
    private String name;
    private String uen;    
    private String email;
    
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	public String getUen() {
		return uen;
	}
	public void setUen(String uen) {
		this.uen = uen;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "CompanyDto [id=" + id + ", name=" + name + ", uen=" + uen + ", email="
				+ email + "]";
	}
}
