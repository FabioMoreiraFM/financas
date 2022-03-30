package com.financas.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.financas.api.model.TipoReceitaModel;
import com.financas.domain.model.TipoReceita;

@Component
public class TipoReceitaConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public void copyToDomainObject(TipoReceitaModel tipoReceitaModel, TipoReceita tipoReceita) {				
		modelMapper.map(tipoReceitaModel, tipoReceita);
	}
	
	public TipoReceitaModel toModel(TipoReceita tipoReceita) {
		return modelMapper.map(tipoReceita, TipoReceitaModel.class);
	}
}
