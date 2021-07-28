package com.java.spring.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.java.spring.model.Company;

@Repository
public interface CompanyRepo extends CrudRepository<Company,Long> {


}