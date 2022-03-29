package com.financas.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	RECURSO_NAO_ENCONTRADO("Recurso não encontrado");
	
	private String title;
	
	ProblemType(String title) {
		this.title = title;
	}
	
}

