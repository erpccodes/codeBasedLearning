package com.example.Journal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.Journal.service.UserDetailsServiceImpl;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;


@Configuration
public class SpringSecurity{
	
	@Autowired
	private UserDetailsServiceImpl userdetailsServiceImpl;
	
	   @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests((authz) -> authz
	            		.requestMatchers("journal/**").authenticated()
	            		.requestMatchers("user/perform/**").authenticated()
	            		.requestMatchers("user/add").permitAll()
	            		.requestMatchers("user/admin/**").hasRole("ADMIN")
	            		.anyRequest().authenticated()
	            )
	            .httpBasic(withDefaults())
	        .csrf(csrf->csrf.disable()); //Cross-Site Request Forgery, disabling since we are not sending request from browser otherwise it wont allow request other than get
	        return http.build();
	    }
	   
	
	   // Bean for AuthenticationManager with custom UserDetailsService and PasswordEncoder
	    @Bean
	    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
	        AuthenticationManagerBuilder authenticationManagerBuilder = 
	            http.getSharedObject(AuthenticationManagerBuilder.class);  //retrieves an instance of AuthenticationManagerBuilder that is shared within the Spring Security context without creating a new instance.
	        
	        authenticationManagerBuilder.userDetailsService(userdetailsServiceImpl)
	            .passwordEncoder(passwordEncoder);

	        return authenticationManagerBuilder.build();
	    }

	  
	   
	    // Bean for PasswordEncoder using BCrypt
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    
}
