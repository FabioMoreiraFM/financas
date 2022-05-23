package com.financas.testes.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.financas.testes.utils.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class TipoDespesaIT extends GenericIT {
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
		RestAssured.basePath = "/tipos-despesas";
	}
	
	@Test
	void listarQuantidadeCorretaTipoDespesa() {
		given()
			.auth()
				.oauth2(accessToken)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(2));
	}
	
	@Test
	void pegarTipoDespesaCorretaPorId() {
		given()
			.auth()
				.oauth2(accessToken)
			.accept(ContentType.JSON)
			.pathParam("tipoDespesaId", 1L)
		.when()
			.get("/{tipoDespesaId}")
		.then()
			.body("descricao", equalTo("Boleto"));
	}
	
	@Test
	void pesquisarTipoDespesaNaoCadastrada() {
		given()
			.auth()
				.oauth2(accessToken)
			.accept(ContentType.JSON)
			.pathParam("tipoDespesaId", 5L)
		.when()
			.get("/{tipoDespesaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	void deletarTipoDespesaPorId() {
		given()
			.auth()
				.oauth2(accessToken)
			.accept(ContentType.JSON)
			.pathParam("tipoDespesaId", 2L)
		.when()
			.delete("/{tipoDespesaId}")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void deletarTipoDespesaEmUsoPorId() {
		given()
			.auth()
				.oauth2(accessToken)
			.accept(ContentType.JSON)
			.pathParam("tipoDespesaId", 1L)
		.when()
			.delete("/{tipoDespesaId}")
		.then()
			.statusCode(HttpStatus.CONFLICT.value());
	}
	
	@Test
	void adicionarTipoDespesa() {
		String jsonCorretoNovoTipoDespesa = ResourceUtils.getContentFromResource(
				"/json/correto/novo-tipo-despesa.json");
		
		given()
			.auth()
				.oauth2(accessToken)
			.body(jsonCorretoNovoTipoDespesa)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	void adicionarTipoDespesaComDadosFaltantes() {
		String jsonCorretoNovoTipoDespesa = ResourceUtils.getContentFromResource(
				"/json/incorreto/novo-tipo-despesa.json");
		
		given()
			.auth()
				.oauth2(accessToken)
			.body(jsonCorretoNovoTipoDespesa)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
}
