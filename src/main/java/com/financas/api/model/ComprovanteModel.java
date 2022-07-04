package com.financas.api.model;

import lombok.Data;

@Data
public class ComprovanteModel {

	private Long id;
	
	private String nome;
	
	private String descricao;
	
	private String contentType;
	
	private Long tamanho;
	
}
