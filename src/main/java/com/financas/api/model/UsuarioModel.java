package com.financas.api.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UsuarioModel {
	
	private Long id;
	
	@NotBlank
	private String nome;
	
	@Email
	private String email;
		
	@NotBlank
	private String celular;

	private EnderecoModel endereco;	
}
