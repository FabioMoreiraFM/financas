package com.financas.domain.model;

import lombok.Data;

@Data
public class Auditoria {

	private Long id;
	
	private String descricao;
	
	private String tipoOperacao;
	
	private String tabela;
	
	private String nomeUsuario;
	
	private String mapObjetoAntigo;
	
	private String mapObjetoNovo;
}
