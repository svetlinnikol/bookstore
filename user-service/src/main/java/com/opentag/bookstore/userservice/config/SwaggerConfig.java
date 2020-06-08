package com.opentag.bookstore.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration class with defined based path for the controllers.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    private static final String DEFAULT_INCLUDE_CONTROLLER_PATH = "com.opentag.bookstore.userservice.controller";

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage(DEFAULT_INCLUDE_CONTROLLER_PATH))              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
}