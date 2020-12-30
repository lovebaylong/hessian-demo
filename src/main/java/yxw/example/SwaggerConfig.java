package yxw.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ImplicitGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static springfox.documentation.builders.PathSelectors.ant;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
//@EnableSwagger //Enable swagger 1.2 spec
//@EnableSwagger2 //Enable swagger 2.0 spec
@EnableOpenApi //Enable open api 3.0.3 spec
public class SwaggerConfig {

    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user-open-api")
                .select()
                .paths(userApiPaths())
                .apis(RequestHandlerSelectors.withClassAnnotation(io.swagger.annotations.Api.class))
                .build()
                //.securitySchemes(Collections.singletonList(oauth()))
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()))
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket userAccountApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user-account-open-api")
                .select()
                .paths(userAccountApiPaths())
                .apis(RequestHandlerSelectors.withClassAnnotation(io.swagger.annotations.Api.class))
                .build()
                .apiInfo(apiInfo());
    }

    private Predicate<String> userApiPaths() {
        return regex(".*/openapi/user/v1.*");
    }

    private Predicate<String> userAccountApiPaths() {
        return regex(".*/openapi/user/account/v1.*");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Demo对外开放接口API文档")
                .description("尹先文的示例对外开放接口")
                .version("1.0.0")
                .termsOfServiceUrl("https://github.com/lovebaylong")
                .license("lovebaylong")
                .licenseUrl("https://github.com/lovebaylong")
                .build();
    }

    @Bean
    SecurityScheme apiKey() {
        return new ApiKey("token", "token", "header");
    }

    @Bean
    SecurityContext securityContext() {
        SecurityReference securityReference = SecurityReference.builder()
                .reference("demoClient_auth")
                .scopes((AuthorizationScope[]) scopes().toArray())
                .build();

        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(securityReference))
                .forPaths(ant(".*/openapi/user/v1.*"))
                .build();
    }

    @Bean
    SecurityScheme oauth() {
        return new OAuthBuilder()
                .name("demoClient_auth")
                .grantTypes(grantTypes())
                .scopes(scopes())
                .build();
    }

    List<GrantType> grantTypes() {
        GrantType grantType = new ImplicitGrantBuilder()
                .loginEndpoint(new LoginEndpoint("http://localhost:8080/democlient/api/oauth/dialog"))
                .build();
        return Collections.singletonList(grantType);
    }

    List<AuthorizationScope> scopes() {
        return Arrays.asList(
                new AuthorizationScope("write:user", "修改用户权限"),
                new AuthorizationScope("read:user", "读取用户权限"));
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return SecurityConfigurationBuilder.builder()
                .clientId("abc")
                .clientSecret("123")
                .realm("users")
                .appName("usersManage")
                .scopeSeparator(",")
                .build();
    }
}