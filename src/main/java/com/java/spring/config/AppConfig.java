package com.java.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:datasource.properties")
public class AppConfig {
    @Autowired
    DataSourceProperties dataSourceProperties;

   
    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(this.dataSourceProperties.determineDriverClassName());
        dataSourceBuilder.url(this.dataSourceProperties.getUrl());
        dataSourceBuilder.username(this.dataSourceProperties.getUsername());
        dataSourceBuilder.password(this.dataSourceProperties.getPassword());
        return dataSourceBuilder.build();
    }
}