package com.financas.api.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TipoDespesaModel {

	Long id;
	
	@NotBlank
	String descricao;
	
}
