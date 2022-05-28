package com.financas.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TipoReceitaInputModel {
	
	@NotBlank
	private String descricao;
}
