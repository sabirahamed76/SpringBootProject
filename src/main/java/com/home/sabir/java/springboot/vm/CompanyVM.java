package com.home.sabir.java.springboot.vm;

public class CompanyVM {
	
	private String name;
    private long id;
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

    public String toString(){
        return id+" | " + name;
    }
}
