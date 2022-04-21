package com.financas.api.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TerceiroModel {

	private Long id;
	
	@NotBlank
	private String descricao;
	
	@NotBlank
	private String documento;
	
	@NotBlank
	private String tipoTerceiro;
	
}
