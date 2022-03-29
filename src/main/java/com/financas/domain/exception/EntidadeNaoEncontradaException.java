package com.financas.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public EntidadeNaoEncontradaException(EnumEntidadeException regras, Long regrasId) {
		this(String.format("O recurso %s de código %d não existe.", regras.getTipo(), regrasId));
	}

}
