package by.imsha.rest.docs;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;


//@Configuration
//@EnableSwagger2
//@ComponentScan("by.imsha.api.rest")
public class SwaggerConfig {

    public static final String DEFAULT_INCLUDE_PATTERNS = "/api/.*";
    @Bean
    public Docket swaggerSettings() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("by.imsha.api.rest"))
                .paths(PathSelectors.ant("/api*"))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder().build();
    }
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Imsha.by REST API",
                "Rest api for managing base entities in system.",
                "1.0",
                "Terms of service",
                "andrei.misan@gmail.com",
                "License of API",
                "API license URL");
        return apiInfo;

    }
}
