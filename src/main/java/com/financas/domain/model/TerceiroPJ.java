package com.financas.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TerceiroPJ extends Terceiro {

	private String cnpj;
	
	@Builder
	public TerceiroPJ(String cnpj, String nome) {
		super(nome);		
		this.cnpj = cnpj;
	}
}
