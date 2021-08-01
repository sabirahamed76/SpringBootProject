package com.home.sabir.java.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity (name = "admin_sys_company")
public class Company {

   
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="uen")
    private String uen;
    
    @Column(name="email")
    private String email;
    
    
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    public Company(long id, String name) {
		this.id = id;
		this.name = name;
	}
    
    public Company() {
    
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
		return "Company [id=" + id + ", name=" + name + ", uen=" + uen + ", email=" + email
				+ "]";
	}



}