package com.financas.testes.api;

import static io.restassured.RestAssured.given;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class GenericIT {

	@Autowired
	protected Flyway flyway;
	
	@LocalServerPort
	protected int port;
	
	protected String accessToken;
	
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		
		flyway.migrate();
		
		accessToken = getAccessToken();
	}
	
	protected String getAccessToken() {
		RestAssured.basePath = "/oauth/token";
		
		Response response = given()
			.contentType("application/x-www-form-urlencoded; charset=utf-8")
			.formParam("username", "teste@teste.com")
			.formParam("password", "123")
			.formParam("grant_type", "password")
		.auth()
			.basic("financas-web", "web123")
		.when()
			.post();
		
		return response.getBody().jsonPath().get("access_token");
	}
}