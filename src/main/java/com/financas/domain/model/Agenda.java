package com.financas.domain.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Agenda {

	private Long id;
	
	private String titulo;
	
	private String msg;
	
	private String email;
	
	private Integer qtdEnvio;
	
	private LocalDate dtPrimeiroEnvio;
	
	private LocalDate dtUltimoEnvio;	
	
	private Boolean envioFinalizado;
}
