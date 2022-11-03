package com.example.demo.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    private final Response m201 = simpleMessage("201", "Resource created");
    private final Response m204put = simpleMessage("204", "Resource updated");
    private final Response m204del = simpleMessage("204", "Resource removed");
    private final Response m403 = simpleMessage("403", "Forbidden");
    private final Response m404 = simpleMessage("404", "Not found");
    private final Response m422 = simpleMessage("422", "Validation error");
    private final Response m500 = simpleMessage("500", "Internal Error");
    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
            .useDefaultResponseMessages(false)
            .globalResponses(HttpMethod.GET, Arrays.asList(m403, m404, m500))
            .globalResponses(HttpMethod.POST, Arrays.asList(m201, m403, m422, m500))
            .globalResponses(HttpMethod.PUT, Arrays.asList(m204put, m403, m404, m422, m500))
            .globalResponses(HttpMethod.DELETE, Arrays.asList(m204del, m403, m404, m422, m500))
            .select()                                  
            .apis(RequestHandlerSelectors.basePackage("com.example.demo.resources"))              
            .paths(PathSelectors.any())                          
            .build()
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
            "Api of the course", 
            "This API is used in the course of springboot", 
            "Version 1.0", 
            null, 
            new Contact("Pedro Henrique", null, "pedro.h8237@gmail.com"), 
            "Use allowed to students", 
            null, 
            Collections.emptyList()
        );
    }

    private Response simpleMessage(String code, String msg) {
        return new ResponseBuilder().code(code).description(msg).build();
    }

}