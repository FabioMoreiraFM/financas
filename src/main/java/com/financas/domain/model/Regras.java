package com.financas.domain.model;

import lombok.Data;

@Data
public class Regras {
	private Long id;
	
	private Integer qtdMaxEnvioAgendamento;
	
	private Double fatorDespesaReceitaParaEnvioEmail;
	
	private Boolean enviarEmailDespesasAtrasadas;
}
