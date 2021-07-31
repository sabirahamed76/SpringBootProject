package com.home.sabir.java.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.home.sabir.java.springboot.dao.CompanyDao;
import com.home.sabir.java.springboot.dao.impl.CompanyDaoImpl;
import com.home.sabir.java.springboot.dto.CompanyDto;
import com.home.sabir.java.springboot.model.Company;
import com.home.sabir.java.springboot.repo.CompanyRepo;
import com.home.sabir.java.springboot.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	private static Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    CompanyDao companyDao;
    
    @Autowired
    CompanyRepo companyRepo;
	
	@Autowired 
	ModelMapper modelMapper;
	
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
		Iterable<com.home.sabir.java.springboot.model.Company> companylist = companyRepo.findAll();
        for(com.home.sabir.java.springboot.model.Company company:companylist){
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
	    Company company = modelMapper.map(companyDto, Company.class);
	    return company;
	}
	
	public CompanyDto convertToDto(Company company) throws ParseException {
		CompanyDto companyDto= modelMapper.map(company, CompanyDto.class);
	    return companyDto;
	}
    
}
