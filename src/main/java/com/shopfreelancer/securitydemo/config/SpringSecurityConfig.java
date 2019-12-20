package com.shopfreelancer.securitydemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .csrf().ignoringAntMatchers("/h2-console/**").disable()
                .authorizeRequests().antMatchers("/").permitAll()
                .and().authorizeRequests().antMatchers("/protected").authenticated()
                .and().formLogin().usernameParameter("name").permitAll()
                .and().exceptionHandling().accessDeniedPage("/no-access");
    }
}