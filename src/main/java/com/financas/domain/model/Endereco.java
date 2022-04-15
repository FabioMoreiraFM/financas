package com.financas.domain.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Endereco {

	private String cep;
	
	private String logradouro;
	
	private String bairro;
	
	private String cidade;
	
	private String uf;	
}
