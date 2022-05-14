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
public class ReceitaInputModel {

	private Long id;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private BigDecimal valor;
	
	@Valid
	@NotNull
	private IdInputGenerico fonte;
	
	@NotNull
	private LocalDate dtInicio;
	
	private LocalDate dtFim;
	
	@Valid
	@NotNull
	private IdInputGenerico tipoReceita;	
	
}
