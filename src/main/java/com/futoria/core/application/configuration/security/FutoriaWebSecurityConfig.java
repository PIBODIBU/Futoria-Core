package com.futoria.core.application.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

public class FutoriaWebSecurityConfig extends WebSecurityConfigurerAdapter {
    protected DataSource dataSource;
    private FutoriaUserDetailsServiceImpl userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                        "select username,password,is_enabled from sys_user where username=?")
                .authoritiesByUsernameQuery(
                        "select username,role_name from sys_rel_user_role where username=?");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    // Beans
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Autowiring
    @Autowired
    public void setUserDetailsService(@Qualifier("CoreUserDetailsService")
                                              FutoriaUserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}