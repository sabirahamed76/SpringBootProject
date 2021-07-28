package com.java.spring.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.spring.model.Company;

@Repository
public interface CompanyJpaRepo extends JpaRepository<Company, Long> {
	List<Company> findById(long id);
}
