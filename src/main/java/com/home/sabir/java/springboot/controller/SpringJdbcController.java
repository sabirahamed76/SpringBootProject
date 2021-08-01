package com.home.sabir.java.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.home.sabir.java.springboot.dto.CompanyDto;
import com.home.sabir.java.springboot.model.Company;
import com.home.sabir.java.springboot.repo.CompanyJpaRepo;
import com.home.sabir.java.springboot.service.CompanyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/SpringJdbc")
public class SpringJdbcController {
	private static Logger logger = LoggerFactory.getLogger(SpringJdbcController.class);
	
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
    
    @RequestMapping(value = "/getCompany", produces = "application/json")	    
    @ResponseStatus(value = HttpStatus.OK)	
   	public ResponseEntity<CompanyDto> getCompany(@RequestParam(required = false) Long id) {
   		try {
   			List<Company> companies = companyJpaRepo.findById(id.longValue());
			CompanyDto cDto=null;
			for (Company company : companies) {				
				//if (id==Long.valueOf(company.getId())) {
					cDto=new CompanyDto();
					cDto.setId(company.getId());
					cDto.setName(company.getName());
					cDto.setEmail(company.getEmail());
					cDto.setUen(company.getUen());
				//}				
			}
			return new ResponseEntity<>(cDto, HttpStatus.OK);
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
	public ResponseEntity<CompanyDto> updateCompany(@PathVariable("id") Long id) {
		try {
				List<Company> companies = companyJpaRepo.findById(id.longValue());
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
	
	@RequestMapping(value = "/viewCompany", produces = "application/json")	    
	@ResponseStatus(value = HttpStatus.OK) 
	public ResponseEntity<CompanyDto> viewCompany(@RequestParam(required = false) Long id) {
		try {
				List<Company> companies = companyJpaRepo.findById(id.longValue());
				CompanyDto cDto=null;	
				for (Company company : companies) {
					cDto=companyService.convertToDto(company);	
				}
				return new ResponseEntity<>(cDto, HttpStatus.OK);
			} catch (Exception e) {
				logger.error(e.getMessage());
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
