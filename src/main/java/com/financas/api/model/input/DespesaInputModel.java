package com.financas.api.model.input;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DespesaInputModel {
	
	private Long id;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private BigDecimal valorParcela;
	
	@NotNull
	private Integer totalParcelas;
	
	@Valid
	@NotNull
	private IdInputGenerico credor;
	
	@NotNull
	private LocalDate mesAnoInicioPgto;
	
	@NotNull
	private Integer diaVencimentoParcela;

	@Valid
	@NotNull
	private IdInputGenerico tipoDespesa;
	
	@Valid
	@NotNull
	private IdInputGenerico usuario;
}
