package br.com.erudio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("RestFul API with Java 18 and Spring Boot 3")
                        .version("v1")
                        .description("Some description")
                        .termsOfService("https://www.springboot.com.br/")
                        .license(new License().name("Apache 2.0")
                                .url("https://www.springboot.com.br/")));
    }
}
