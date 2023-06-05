package com.cadastro.profissionalestabelecimento.infra.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI configOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("API cadastramento de estabelecimentos e profissionais")
                        .description("Documentacao da API cadastramento"));
    }
}
