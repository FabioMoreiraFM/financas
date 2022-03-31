package com.financas.api.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TerceiroPJModel {

	private Long id;
	
	@NotBlank
	private String descricao;
	
	@NotBlank
	private String cnpj;
	
}
