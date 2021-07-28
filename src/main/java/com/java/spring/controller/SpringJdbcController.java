package com.java.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.java.spring.dto.CompanyDto;
import com.java.spring.model.Company;
import com.java.spring.repo.CompanyJpaRepo;
import com.java.spring.service.CompanyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SpringJdbcController {
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	CompanyJpaRepo companyJpaRepo;


    @RequestMapping(value = "/getAllCompanies", produces = "application/json")	    
	@ResponseBody 
	public ResponseEntity<List<Company>> getAllCompanies(@RequestParam(required = false) String title) {
		try {
			List<Company> companies = new ArrayList<Company>();

			companyJpaRepo.findAll().forEach(companies::add);
			if (companies.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(companies, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
	
	@RequestMapping(value = "/createCompany", produces = "application/json")	  
	@ResponseStatus(value = HttpStatus.OK)	  
	@ResponseBody 
	public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto cDto) {
		  try {
			  Company company = companyJpaRepo
						.save(new Company(cDto.getId(), cDto.getName()));
			  CompanyDto companyDto = companyService.convertToDto(company);
				return new ResponseEntity<>(companyDto, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	  
	  
	@RequestMapping(value = "/modifyCompany/{id}", produces = "application/json")	    
	@ResponseBody 
	public ResponseEntity<CompanyDto> updateCompany(@PathVariable("id") long id) {
		try {
				List<Company> companies = companyJpaRepo.findById(id);
				CompanyDto cDto=null;
				for (Company company : companies) {
					cDto=new CompanyDto();
					company.setName(cDto.getName());
					companyJpaRepo.save(company);
				}
				return new ResponseEntity<>(cDto, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}

	}
	
	@RequestMapping(value = "/viewCompany/{id}", produces = "application/json")	    
	@ResponseBody 
	public ResponseEntity<CompanyDto> viewCompany(@PathVariable("id") long id) {
		try {
				List<Company> companies = companyJpaRepo.findById(id);
				CompanyDto cDto=null;	
				for (Company company : companies) {
					cDto=companyService.convertToDto(company);	
				}
				return new ResponseEntity<>(cDto, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}

	}

	

	@RequestMapping(value = "/deleteCompany/{id}", produces = "application/json")	    
	@ResponseBody 
		public ResponseEntity<HttpStatus> deleteCompany(@PathVariable("id") long id) {
		try {
			companyJpaRepo.deleteById(Long.valueOf(id));
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/deleteAllCompanies", produces = "application/json")	    
	@ResponseBody 
	public ResponseEntity<HttpStatus> deleteAllCompanies() {
			try {
				companyJpaRepo.deleteAll();
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	
	}
		
	  
	 
    
}
