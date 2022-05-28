package com.financas.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputComSenhaModel extends UsuarioInputModel{

	@NotBlank
	private String senha;
	
}
