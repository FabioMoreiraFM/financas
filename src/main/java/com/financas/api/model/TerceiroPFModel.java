package com.financas.api.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TerceiroPFModel {

	private Long id;
	
	@NotBlank
	private String descricao;
	
	@NotBlank
	private String cpf;
	
}
