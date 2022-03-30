package com.financas.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Despesa {
	private Long id;
	
	private String descricao;
	
	private BigDecimal valor;
	
	private Integer parcela;
	
	private Terceiro credor;
	
	private LocalDate dtInicio;
	
	private LocalDate dtFim;
	
	private LocalDate dtVencimento;
	
	private LocalDate dtPago;
		
	private TipoDespesa tipoDespesa;
	
	private Boolean paga;
	
	private Comprovante comprovantePgto;
	
}
