package crocobob.CIENderella.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "CIEN데렐라 API 문서",
                version = "0.1",
                description = "API 문서입니다.",
                contact = @Contact(
                        name = "CIEN",
                        email = "mmungjun@gmail.com")),
        security = @SecurityRequirement(name = "bearerAuth"))
public class SwaggerConfig {

}
