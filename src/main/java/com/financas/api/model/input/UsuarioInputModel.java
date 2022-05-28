package com.financas.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.financas.api.model.EnderecoModel;

import lombok.Data;

@Data
public class UsuarioInputModel {
	
	@NotBlank
	private String nome;
	
	@Email
	private String email;
		
	@NotBlank
	private String celular;

	private EnderecoModel endereco;	
}
