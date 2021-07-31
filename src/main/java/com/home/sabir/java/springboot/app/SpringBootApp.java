package com.home.sabir.java.springboot.app;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.home.sabir.java.springboot.repo.CompanyRepo;

@SpringBootApplication
@EnableJpaRepositories("com.home.sabir.java.springboot.repo")
@EntityScan("com.home.sabir.java.springboot")
@ComponentScan("com.home.sabir.java") 
public class SpringBootApp implements CommandLineRunner {

    @Autowired
    DataSource dataSource;

   
    @Autowired
    CompanyRepo companyRepo;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Our DataSource is = " + dataSource);
        Iterable<com.home.sabir.java.springboot.model.Company> companylist = companyRepo.findAll();
        for(com.home.sabir.java.springboot.model.Company companymodel:companylist){
            System.out.println("Company Detail: " + companymodel.toString());
        }
    }
    
    @Bean
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
}
