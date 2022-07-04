package com.financas.domain.exception;

public enum EnumEntidadeException {

	Regras("regras"),
	TipoDespesa("tipos-despesas"),
	TipoReceita("tipos-receitas"),
	Terceiro("terceiros"),
	Usuarios("usuarios"),
	Despesas("despesas"),
	Receitas("receitas"),
	Parcelas("parcelas"),
	Comprovante("comprovante");
	
	private String tipo;
	
	private EnumEntidadeException(String tipo) {
		this.setTipo(tipo);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
