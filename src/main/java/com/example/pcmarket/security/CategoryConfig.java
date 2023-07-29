package com.example.pcmarket.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class CategoryConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("125")).roles("USER")
                .and()
                .withUser("manager").password(passwordEncoder().encode("125")).roles("MANAGER")
                .and()
                .withUser("direktor").password(passwordEncoder().encode("125")).roles("DIREKTOR");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET,"/category/*").hasAnyRole("USER","MANAGER","DIREKTOR")
                .antMatchers(HttpMethod.GET,"/category/page/*").hasAnyRole("USER","MANAGER","DUREKTOR")
                .antMatchers(HttpMethod.GET,"/category/**").hasAnyRole("MANAGER", "DIREKTOR")
                .antMatchers(HttpMethod.PUT,"/category/*").hasAnyRole("MANAGER","DIREKTOR")
                .antMatchers("/category/**").hasRole("DIREKTOR")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
