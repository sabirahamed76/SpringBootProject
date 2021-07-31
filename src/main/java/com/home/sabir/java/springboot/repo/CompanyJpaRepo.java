package com.home.sabir.java.springboot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.home.sabir.java.springboot.model.Company;

@Repository
public interface CompanyJpaRepo extends JpaRepository<Company, Long> {
	List<Company> findById(long id);
}
