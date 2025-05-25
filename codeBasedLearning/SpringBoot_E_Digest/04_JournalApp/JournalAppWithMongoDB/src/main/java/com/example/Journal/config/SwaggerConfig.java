package com.example.Journal.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

	  @Bean
	    public OpenAPI myCustomConfig(){
	        return new OpenAPI()
	                .info(
	                new Info().title("Journal App APIs")
	                        .description("By Himanshu")
	                )
	                .servers(Arrays.asList(new Server().url("http://localhost:8282").description("local"),
	                        new Server().url("http://localhost:8283").description("live")))
	            .tags(Arrays.asList(
	                        new Tag().name("Public APIs"),
	                        new Tag().name("User APIs"),
	                        new Tag().name("Journal APIs"),
	                        new Tag().name("Admin APIs")
	                ))
	                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
	                .addSecurityItem(new SecurityRequirement().addList("googleOAuth", List.of("openid","email","profile")))
	                .components(new Components()
	                        .addSecuritySchemes("bearerAuth",
	                                new SecurityScheme()
	                                        .type(SecurityScheme.Type.HTTP)
	                                        .scheme("bearer")
	                                        .bearerFormat("JWT")
	                                        .in(SecurityScheme.In.HEADER)
	                                        .name("Authorization")
	                        )
	                        .addSecuritySchemes("googleOAuth",
	                                new SecurityScheme()
	                                        .type(SecurityScheme.Type.OAUTH2)
	                                        .flows(new io.swagger.v3.oas.models.security.OAuthFlows()
	                                                .authorizationCode(new OAuthFlow()
	                                                        .authorizationUrl("https://accounts.google.com/o/oauth2/v2/auth")
	                                                        .tokenUrl("http://localhost:8282/auth/google/callback")
	                                                        .scopes(new Scopes()
	                                                        		.addString("openid", "OpenID Connect scope")
	                                                        		.addString("email",   "Grants access to the user’s email address")
	                                                        		.addString("profile", "Grants access to basic profile info (name, picture)")
	                                                        		)
	                                                )
	                                        )
	                        )
	                );
	    }
	}