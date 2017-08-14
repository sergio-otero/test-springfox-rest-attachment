package com.example.testspringfoxrestattachment;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.collect.Lists.*;
import static springfox.documentation.schema.AlternateTypeRules.*;
@SpringBootApplication
@EnableSwagger2
public class TestSpringfoxRestAttachmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSpringfoxRestAttachmentApplication.class, args);
	}
	
	@Bean
	  public Docket petApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	          .apis(RequestHandlerSelectors.any())
	          .paths(PathSelectors.any())
	          .build()
	        .pathMapping("/")
	        .directModelSubstitute(LocalDate.class,
	            String.class)
	        .genericModelSubstitutes(ResponseEntity.class)
	        .alternateTypeRules(
	            newRule(typeResolver.resolve(DeferredResult.class,
	                    typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
	                typeResolver.resolve(WildcardType.class)))
	        ;
	  }

	  @Autowired
	  private TypeResolver typeResolver;


	  @Bean
	  UiConfiguration uiConfig() {
	    return new UiConfiguration(
	        "validatorUrl",// url
	        "none",       // docExpansion          => none | list
	        "alpha",      // apiSorter             => alpha
	        "schema",     // defaultModelRendering => schema
	        UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
	        false,        // enableJsonEditor      => true | false
	        true,         // showRequestHeaders    => true | false
	        60000L);      // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)
	  }
	

}
