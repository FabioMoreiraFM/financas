package com.financas.api.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TipoReceitaModel {
	
	private Long id;
	
	@NotBlank
	private String descricao;
}
