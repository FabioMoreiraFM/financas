package com.financas.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TerceiroPF extends Terceiro {

	private String cpf;
	
	@Builder
	public TerceiroPF(String cpf, String nome) {
		super(nome);	
		this.cpf = cpf;
	}

}
