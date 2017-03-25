package com.futoria.core.application.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class FutoriaAuthenticationProviderConfig {
    @Bean(name = "CoreDataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/futoria");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}
