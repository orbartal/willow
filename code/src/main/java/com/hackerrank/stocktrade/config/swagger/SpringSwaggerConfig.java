package com.hackerrank.stocktrade.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringSwaggerConfig {

	@Bean
	public Docket swaggerSettings() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.apiInfo(getApiInfo());
		docket.useDefaultResponseMessages(false);
		ApiSelectorBuilder asb = getBuilder(docket);
		docket = asb.build();
		return docket;
	}

	private ApiInfo getApiInfo() {
		Contact contact = new Contact("Or Bartal", "https://github.com/", "orbartal@gmail.com");
		ApiInfoBuilder api = new ApiInfoBuilder();
		api.title("Example Swagger API");
		api.description("A demo micro service spring boot back end");
		api.contact(contact);
		api.version("1.0.0");
		api.license("GNU");
		api.licenseUrl("https://www.gnu.org/licenses/gpl.html");
		return api.build();
	}

	private ApiSelectorBuilder getBuilder(Docket docket) {
		ApiSelectorBuilder asb = docket.select().apis(RequestHandlerSelectors.any());
		asb.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")));
		asb.paths(Predicates.not(PathSelectors.regex("/error.*")));
		return asb;
	}

}