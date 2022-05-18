package com.financas.infrastructure.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;
import com.financas.api.exceptionHandler.ProblemDetails;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDocket() {
		TypeResolver typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.financas.api"))
					.paths(PathSelectors.any())
					.build()
					.useDefaultResponseMessages(false)
					.additionalModels(typeResolver.resolve(ProblemDetails.class))
					.tags(new Tag("Despesas", "Gerencia as despesas do usuário"), listarTagsRestantes())
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Finanças API")
				.description("Documentação da API de Finanças.")
				.version("1")
				.build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	private Tag[] listarTagsRestantes() {
		Tag receita = new Tag("Receitas", "Gerencia as receitas do usuário");
		Tag regras = new Tag("Regras", "Gerencia as configurações parametrizáveis do sistema");
		Tag terceiro = new Tag("Terceiros", "Gerencia terceiros (Credores ou fonte das receitas)");
		Tag despesas = new Tag("Tipos de Despesa", "Gerencia os tipos de despesa (Cartão de crédito, boleto, etc..)");
		Tag receitas = new Tag("Tipos de Receita", "Gerencia os tipos de receita (Salário, dividendos, etc..)");
		Tag usuario = new Tag("Usuários", "Gerencia os usuários do sistema");
		
		return new Tag[]{receita, regras, terceiro, despesas, receitas, usuario};
	}
	
}
