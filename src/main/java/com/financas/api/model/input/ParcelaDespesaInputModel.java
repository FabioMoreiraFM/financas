package com.financas.api.model.input;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParcelaDespesaInputModel {

	private LocalDate dtPagamento;
	
	private Long idParcela;
}
