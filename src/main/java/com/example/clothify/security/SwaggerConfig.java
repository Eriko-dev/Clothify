package com.example.clothify.security;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiDoc() {
        Info apiInfo = new Info()
                .title("Clothify API Documentation")
                .description("API documentation for Clothify E-commerce System")
                .version("1.0.0");

        return new OpenAPI().info(apiInfo);
    }
}
