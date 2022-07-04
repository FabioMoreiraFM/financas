package com.financas.domain.exception;

public class SolicitacaoInconsistenteException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public SolicitacaoInconsistenteException(String mensagem) {
		super(mensagem);
	}
	
	public SolicitacaoInconsistenteException(EnumEntidadeException ex, String mensagem) {
		this(String.format("A solicitação não pode ser executada. Motivo: {}", mensagem));
	}

}
