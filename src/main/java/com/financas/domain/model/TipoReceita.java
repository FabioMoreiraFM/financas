package com.financas.domain.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TipoReceita {

	private Long id;
	
	@NotBlank
	private String descricao;

}
