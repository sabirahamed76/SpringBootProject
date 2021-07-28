package com.java.spring.dao;

import java.util.List;

import com.java.spring.model.Company;


public interface CompanyDao {

	public int saveCompany(Company c);
	
	public int updateCompany(Company c);
	
	public int deleteCompany(Company c);
	
	public Boolean saveCompanyByPreparedStatement(Company c);	
	
	public  void saveCompanyByNamedParameterJdbcTemplate (Company c);
	
	public List<Company> getAllCompaniesByResultSetExtractor();
	
}