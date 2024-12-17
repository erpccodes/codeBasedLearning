package com.example.Journal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SpringSecurity{
	
	   @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests((authz) -> authz
	            		.requestMatchers("journal/**").authenticated()
	            		.requestMatchers("user/**").authenticated()
	            		.anyRequest().authenticated()
	            )
	            .httpBasic(withDefaults())
	        .csrf(csrf->csrf.disable()); //Cross-Site Request Forgery, disabling since we are not sending request from browser otherwise it wont allow request other than get
	        return http.build();
	    }
}
