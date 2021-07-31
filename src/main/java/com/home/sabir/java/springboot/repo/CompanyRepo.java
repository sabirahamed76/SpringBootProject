package com.home.sabir.java.springboot.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.home.sabir.java.springboot.model.Company;

@Repository
public interface CompanyRepo extends CrudRepository<Company,Long> {


}