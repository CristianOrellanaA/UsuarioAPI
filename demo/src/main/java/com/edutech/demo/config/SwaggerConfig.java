package com.edutech.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
        .title("Proyecto Edutech de usuarios")
        .version("1.0")
        .description("Microservicio de Usuarios")
        );
    }
}
