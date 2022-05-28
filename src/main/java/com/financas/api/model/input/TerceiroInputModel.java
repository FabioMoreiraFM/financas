package com.financas.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TerceiroInputModel {

	@NotBlank
	private String descricao;
	
	@NotBlank
	private String documento;
	
	@NotBlank
	private String tipoTerceiro;
	
}
