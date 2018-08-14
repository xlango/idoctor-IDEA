package com.idoctor.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration  
@EnableWebMvc  
@EnableSwagger2  
@ComponentScan(basePackages="com.idoctor.controller")
public class SwaggerConfig {  
	 @Bean
	    public Docket createRestApi() {//api文档实例

	        return new Docket(DocumentationType.SWAGGER_2)//文档类型：DocumentationType.SWAGGER_2   
	                .select()//构建api选择器
	                .apis(RequestHandlerSelectors.any())//api选择器选择api的包
	                .build()//创建文档
	                . apiInfo(apiInfo());//api信息
	    }

	    private ApiInfo apiInfo() {//接口的相关信息
	        return new ApiInfoBuilder()
	                .title("IA")
	                .description("Base SSM")
	                .termsOfServiceUrl("http://localhost:8080/idoctor/")
	                .contact("XYL")
	                .version("1.0")
	                .build();
	    }
}  