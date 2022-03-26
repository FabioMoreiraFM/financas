package com.financas.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.financas.api.model.RegrasModel;
import com.financas.domain.model.Regras;

@Component
public class RegrasConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public void copyToDomainObject(RegrasModel regrasModel, Regras regras) {				
		modelMapper.map(regrasModel, regras);
	}
	
	public RegrasModel toModel(Regras regras) {
		return modelMapper.map(regras, RegrasModel.class);
	}
	
	
}
