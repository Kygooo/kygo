package com.kygo.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

	@Bean
	public Docket createRestApi() {
		logger.debug("call createRestApi");
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.kygo.api.controller"))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		logger.debug("http://localhost:8080/swagger-ui.html");
		return new ApiInfoBuilder().title("接口文档").description("项目接口文档")
				.termsOfServiceUrl("http://www.baidu.com/")
				.contact(new Contact("kygo项目组", "www.baidu.com", "kygo@gmail.com")).version("1.0.0").build();
	}
}
