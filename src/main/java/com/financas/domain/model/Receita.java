package com.financas.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Receita {

	private Long id;
	
	private String descricao;
	
	private BigDecimal valor;
	
	private Terceiro fonte;
	
	private LocalDate dtInicio;
	
	private LocalDate dtFim;
	
	private TipoReceita tipoReceita;	
	
	private Comprovante recibo;
}
