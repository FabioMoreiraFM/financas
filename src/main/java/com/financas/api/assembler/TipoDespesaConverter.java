package com.financas.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.financas.api.model.TipoDespesaModel;
import com.financas.domain.model.TipoDespesa;

@Component
public class TipoDespesaConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public void copyToDomainObject(TipoDespesaModel tipoDespesaModel, TipoDespesa tipoDespesa) {				
		modelMapper.map(tipoDespesaModel, tipoDespesa);
	}
	
	public TipoDespesaModel toModel(TipoDespesa tipoDespesa) {
		return modelMapper.map(tipoDespesa, TipoDespesaModel.class);
	}
	
	public List<TipoDespesaModel> toCollectionModel(List<TipoDespesa> tipoDespesa) {
		return tipoDespesa.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
}
