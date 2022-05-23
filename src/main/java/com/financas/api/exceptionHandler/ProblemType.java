package com.financas.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	RECURSO_NAO_ENCONTRADO("Recurso não encontrado"), 
	DADOS_INVALIDOS("Dados inválidos"),
	MENSAGEM_INCOMPREENSIVEL("Mensagem incompreensível"),
	PARAMETRO_INVALIDO("Parâmetro inválido"),
	ENTIDADE_EM_USO("Entidade em uso");
	
	private String title;
	
	ProblemType(String title) {
		this.title = title;
	}
	
}

