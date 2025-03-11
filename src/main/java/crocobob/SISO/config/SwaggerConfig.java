package crocobob.SISO.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "CIENderella API 문서",
                version = "0.4.1.1"),
        security = @SecurityRequirement(name = "bearerAuth"))
public class SwaggerConfig {}
