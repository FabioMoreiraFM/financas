package com.financas.domain.exception;


public class EntidadeEmUsoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}
	
	public EntidadeEmUsoException(EnumEntidadeException excecao, Long id) {
		this(String.format("O recurso %s de código %d não pode ser removido, pois está em uso.", excecao.getTipo(), id));
	}
}
