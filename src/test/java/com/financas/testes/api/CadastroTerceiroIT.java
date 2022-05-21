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

class CadastroTerceiroIT extends GenericIT {
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
		RestAssured.basePath = "/terceiros";
	}
		
	@Test
	void listarQuantidadeCorretaTerceiros() {
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
	void pegarTerceiroCorretoPorId() {
		given()
			.auth()
				.oauth2(accessToken)
			.accept(ContentType.JSON)
			.pathParam("terceiroId", 1L)
		.when()
			.get("/{terceiroId}")
		.then()
			.body("descricao", equalTo("Terceiro PF"));
	}
	
	@Test
	void deletarTerceiroPJPorId() {
		given()
			.auth()
				.oauth2(accessToken)
			.accept(ContentType.JSON)
			.pathParam("terceiroId", 2L)
		.when()
			.delete("/{terceiroId}")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void adicionarTerceiroPJPorId() {
		String jsonCorretoNovoTerceiro = ResourceUtils.getContentFromResource(
				"/json/correto/novo-terceiro.json");
		
		given()
			.auth()
				.oauth2(accessToken)
			.body(jsonCorretoNovoTerceiro)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}


}
