package com.financas.api.model;

import lombok.Data;

@Data
public class RegrasModel {
	
	private Long id;
	
	private Integer qtdMaxEnvioAgendamento;

	private Double fatorDespesaReceitaEnvioEmail;

	private Boolean enviarEmailDespesasAtrasadas;	
}
