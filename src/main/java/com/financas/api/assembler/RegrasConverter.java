package com.financas.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<RegrasModel> toCollectionModel(List<Regras> regras) {
		return regras.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
}
