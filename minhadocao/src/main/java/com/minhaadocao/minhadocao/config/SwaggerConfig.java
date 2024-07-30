package com.minhaadocao.minhadocao.config;


import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API minhaadoção")
                        .version("1.0.0")
                        .description("API para gerenciar o CRUD do sistema de gestão de adoção"));
    }

	@Bean
    public GroupedOpenApi publicApiCachorro() {
        return GroupedOpenApi.builder()
                .group("Cachorros API")
                .pathsToMatch("/adocao/cachorros/**")
                .build();
    }
    
    @Bean
    public GroupedOpenApi publicApiGato() {
        return GroupedOpenApi.builder()
                .group("Gatos API")
                .pathsToMatch("/adocao/gatos/**")
                .build();
    }
	
}
