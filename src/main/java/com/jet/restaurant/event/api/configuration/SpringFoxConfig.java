package com.jet.restaurant.event.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {


    @Value("${application.name}")
    private String application;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(appInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jet.restaurant.event.api"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag("Restaurant Event", ""), new Tag("Restaurant Event", ""));
    }

    private ApiInfo appInfo() {
        return new ApiInfo(application, "OpenAPI description for \"Restaurant Event\" service", "1.0.0", "", new Contact("", "", ""), "", "", new ArrayList<>());
    }
}
