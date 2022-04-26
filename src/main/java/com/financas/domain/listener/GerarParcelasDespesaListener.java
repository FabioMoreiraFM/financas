package com.financas.domain.listener;

import java.util.List;

import javax.persistence.PostPersist;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.financas.domain.model.Despesa;
import com.financas.domain.model.ParcelaDespesa;
import com.financas.domain.service.ParcelaDespesaService;

public class GerarParcelasDespesaListener {

	@Autowired
	private ObjectFactory<ParcelaDespesaService> parcelaDespesaProvider;
		
	@PostPersist
	public void gerar(Despesa despesa) {		
		ParcelaDespesaService parcelaDespesaService = parcelaDespesaProvider.getObject();
		
		List<ParcelaDespesa> parcelas = parcelaDespesaService.gerar(despesa);
		despesa.setParcelas(parcelas);
	}
}
