package com.hhplus.concert_reservation.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@SecurityScheme(
        type = SecuritySchemeType.APIKEY,
        name = "Authorization", // Swagger UI에서 입력하는 이름
        description = "대기열 Token을 입력해주세요.",
        in = SecuritySchemeIn.HEADER
)
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API Documentation")
                        .version("1.0.0")
                        .description("API documentation with JWT authentication"))
                .security(getSecurityRequirement())
                .components(new Components());
    }

    private List<SecurityRequirement> getSecurityRequirement() {
        List<SecurityRequirement> requirements = new ArrayList<>();
        requirements.add(new SecurityRequirement().addList("Authorization"));
        return requirements;
    }
}
