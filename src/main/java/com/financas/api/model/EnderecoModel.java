package com.financas.api.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EnderecoModel {

	@NotBlank
	private String cep;
	
	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String uf;
	
	@NotBlank
	private String cidade;
	
}
