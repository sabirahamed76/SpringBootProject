package com.home.sabir.java.springboot.service;

import java.util.List;

import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.home.sabir.java.springboot.dto.CompanyDto;
import com.home.sabir.java.springboot.model.Company;

public interface CompanyService {
	
    
	public int saveCompany(CompanyDto cdto);
	public int updateCompany(CompanyDto cdto);
	public int deleteCompany(CompanyDto cdto);
	public List<CompanyDto> getAllCompanies ();
	
	public Company convertToEntity(CompanyDto companyDto) throws ParseException ;
	
	public CompanyDto convertToDto(Company company) throws ParseException;

}
