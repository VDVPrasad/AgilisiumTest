package com.agilisium.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	//	http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
	http.authorizeRequests().antMatchers("/getproductdetails").hasRole("USER").and().httpBasic();	
	http.authorizeRequests().antMatchers("/getconfigdetails").hasRole("ADMIN").and().httpBasic();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("admin").password("{abcd123}password").roles("USER");
		auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance()).withUser("prasad").password("prasad123").roles("USER");
		auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance()).withUser("venkat").password("venkat123").roles("ADMIN");
	}
	
}
	