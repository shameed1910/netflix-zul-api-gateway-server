package com.example.microservices.netflixzulapigatewayserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	public void configure(AuthenticationManagerBuilder auth)throws Exception{
		
		auth.inMemoryAuthentication()
		.withUser("admin").password("{noop}admin_123").roles("ADMIN").and()
		.withUser("user").password("{noop}user_123").roles("USER_ROLE");
		
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
	    .antMatchers(HttpMethod.GET,"/CartService/mycart/**").hasRole("ADMIN")
		.anyRequest().fullyAuthenticated()
		.and().formLogin().permitAll()
		.and().logout().permitAll();
	}

}
