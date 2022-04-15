package com.financas.api.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NovoUsuarioModel extends UsuarioModel{

	@NotBlank
	private String senha;
	
}
