package com.financas.domain.model;

import lombok.Data;
import lombok.NonNull;

@Data
public abstract class Terceiro {

	protected Long id;
	
	@NonNull
	protected String nome;	
}
