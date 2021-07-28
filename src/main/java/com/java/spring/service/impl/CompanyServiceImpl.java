package com.java.spring.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.java.spring.dao.CompanyDao;
import com.java.spring.dto.CompanyDto;
import com.java.spring.model.Company;
import com.java.spring.repo.CompanyRepo;
import com.java.spring.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyDao companyDao;
    
    @Autowired
    CompanyRepo companyRepo;
    
	/*
	 * @Autowired ModelMapper modelMapper;
	 */
    
    public int saveCompany(CompanyDto cDto) {
    	Company c  = convertToEntity(cDto);
    	return companyDao.saveCompany(c);
    	
    }
	public int updateCompany(CompanyDto cDto) {
		Company c  = convertToEntity(cDto);
		return companyDao.updateCompany(c);
		
	}
	public int deleteCompany(CompanyDto cDto) {
		Company c  = convertToEntity(cDto);
		return companyDao.deleteCompany(c);
		
	}
	

	public List<CompanyDto> getAllCompanies (){
		List<CompanyDto> companyList = new ArrayList<CompanyDto>();
		Iterable<com.java.spring.model.Company> companylist = companyRepo.findAll();
        for(com.java.spring.model.Company company:companylist){
        	CompanyDto cDto=convertToDto(company);
        	companyList.add(cDto);        	
        }
        return companyList;		
	}
	

	/*
	 * public CompanyDto getCompanyById (int id) { Optional<Company> company =
	 * companyRepo.findById(Long.parseLong(id+"")); CompanyDto
	 * cDto=convertToDto(company); return cDto; };
	 */
	
    
	
	public Company convertToEntity(CompanyDto companyDto) throws ParseException {
	    Company company =null;//= modelMapper.map(companyDto, Company.class);
	    return company;
	}
	
	public CompanyDto convertToDto(Company company) throws ParseException {
		CompanyDto companyDto=null ;//= modelMapper.map(company, CompanyDto.class);
	    return companyDto;
	}
    
}
