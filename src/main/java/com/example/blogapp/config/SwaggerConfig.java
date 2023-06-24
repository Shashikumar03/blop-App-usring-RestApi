package com.example.blogapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {
    public static final  String AUTHORIZARION_HEADER="Authorazation";

    private ApiKey apiKey() {

       return new ApiKey("JWT",AUTHORIZARION_HEADER,"header");
    }
    private List<SecurityContext> securityContexts(){
        return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
    }
    private List<SecurityReference> sf(){
        AuthorizationScope scope= new AuthorizationScope("global","access EveryThing");
        return  Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scope}));
    }
    @Bean
    public Docket api(){

        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .securityContexts(securityContexts())
                .securitySchemes(Arrays.asList(apiKey()))
                .select().
                apis(RequestHandlerSelectors.any()).
                paths(PathSelectors.any()).build();

    }
    private ApiInfo getInfo(){
        return new ApiInfo("backend cource","project by shashi","1.0","Terms of service",new Contact("shashi","shashi.com","shashi@gmail.com"),"licence","S&t.com", Collections.emptyList());
    }
}
