package com.financas.domain.model;

import lombok.Data;

@Data
public class Usuario {

	private Long id;
	
	private String nome;
	
	private String email;
	
	private String cep;
	
	private String logradouro;
	
	private String bairro;
	
	private String cidade;
	
	private String uf;
	
	private String celular;
	
}
