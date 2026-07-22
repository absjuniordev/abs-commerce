package com.absjrdev.abscommerce.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        Server devServer = new Server()
                .url("http://localhost:8080")
                .description("Development Server");

        Server prodServer = new Server()
                .url("")
                .description("Production Server");

        return new OpenAPI()
                .info(new Info()
                        .title("ABS Commerce API")
                        .version("1.0.0")
                        .description("RESTful API built with Java 17 and Spring Boot for managing an\n" +
                                "e-commerce platform.")
                        .contact(new Contact()
                                .name("Arnaldo Borges dos Santos Junior")
                                .email("abs.junnior@hotmail.com")
                                .url("https://github.com/absjuniordev")))
                .servers(List.of(devServer, prodServer))
                .externalDocs(new ExternalDocumentation()
                        .description("LinkedIn")
                        .url("https://www.linkedin.com/in/absjuniordev/")
                );
    }

}
