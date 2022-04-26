package com.financas.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.financas.domain.model.TipoDespesa;

import lombok.Data;

@Data
public class DespesaModel {
	
	private Long id;
	
	private String descricao;
	
	private BigDecimal valorTotal;
	
	private BigDecimal totalParcelas;
	
	private TerceiroModel credor;
	
	private LocalDate mesAnoInicioPgto;
	
	private Integer diaVencimento;
	
	private TipoDespesa tipoDespesa;
	
	private UsuarioModel usuario;
}
