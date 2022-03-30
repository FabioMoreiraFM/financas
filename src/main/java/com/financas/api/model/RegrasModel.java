package com.financas.api.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RegrasModel {
	
	private Long id;
	
	@NotNull
	private Integer qtdMaxEnvioAgendamento;

	@NotNull
	private Double fatorDespesaReceitaEnvioEmail;

	@NotNull
	private Boolean enviarEmailDespesasAtrasadas;	
}
