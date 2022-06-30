package com.financas.domain.exception;

public class OperacaoJaEfetuadaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public OperacaoJaEfetuadaException(String mensagem) {
		super(mensagem);
	}
	
	public OperacaoJaEfetuadaException(EnumEntidadeException ex, Long idRecurso) {
		this(String.format("A operação solicitada com o recurso %s de código %d já foi executada.", ex.getTipo(), idRecurso));
	}

}
