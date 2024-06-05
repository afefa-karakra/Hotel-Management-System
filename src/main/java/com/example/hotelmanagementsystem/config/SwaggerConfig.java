package com.example.hotelmanagementsystem.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

//    private ApiKey apiKey(){
//        return new ApiKey("Authorization", AUTHORIZATION_HEADER, "header");
//    }

    private Info apiInfo(){
        return new Info().title("title").description("description").version("1")
                .contact(new Contact().name("Basil Assi")
                        .url( "www.tarifi.info")
                        .email( "tarifibasel0@gmail.com"));
//                "Spring Boot Blog REST APIs",
//                "Spring Boot Blog REST API Documentation",
//                "1",
//                "Terms of service",
//                new Contact("Basil Assi", "www.tarifi.info", "tarifibasel0@gmail.com"),
//                "License of API",
//                "API license URL",
//                Collections.emptyList()

    }

    /**
     * In order to create Swagger docket
     * @return
     */
    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes(AUTHORIZATION_HEADER,
                                new SecurityScheme().type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .name(AUTHORIZATION_HEADER)
                                        .bearerFormat("JWT")))
//                                new SecurityScheme().type(SecurityScheme.Type.APIKEY)
//                                        .in(SecurityScheme.In.HEADER)
//                                        .name(AUTHORIZATION_HEADER)))
                .info(apiInfo())
                .addSecurityItem(
                        new SecurityRequirement().addList(AUTHORIZATION_HEADER, Collections.singletonList("global")));
//        return new OpenAPI(DocumentationType.OAS_30)
//                .apiInfo(apiInfo())
//                .securityContexts(Arrays.asList(securityContext()))
//                .securitySchemes(Arrays.asList(apiKey())) /*Enable authorization for APIs in Swagger UI*/
//                .select()
//                .apis(RequestHandlerSelectors.any())/*get all APIs in the project, you can use .basePackage to scan only APIs in specific package like (RequestHandlerSelectors.basePackage("com.edu.controller.customer"))*/
//                .paths(PathSelectors.any())  // Expose all APIs, you can restrict like expose only  .paths(PathSelectors.ant("/posts/*"))
//                .build();
    }

//    private SecurityContext securityContext(){
//        return SecurityContext.builder().securityReferences(defaultAuth()).build();
//    }
//
//    private List<SecurityReference> defaultAuth(){
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        /*
//        * For example, scope could be read, write, and specific API in OAuth2
//         * */
//
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
//    }
}
