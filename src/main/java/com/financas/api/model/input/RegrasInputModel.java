package com.financas.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RegrasInputModel {
	
	@NotNull
	private Integer qtdMaxEnvioAgendamento;

	@NotNull
	private Double fatorDespesaReceitaEnvioEmail;

	@NotNull
	private Boolean enviarEmailDespesasAtrasadas;	
}
