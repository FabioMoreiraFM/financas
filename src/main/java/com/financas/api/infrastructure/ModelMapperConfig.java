package com.financas.api.infrastructure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.financas.api.model.RegrasModel;
import com.financas.api.model.TipoDespesaModel;
import com.financas.domain.model.Regras;
import com.financas.domain.model.TipoDespesa;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		regrasModelToRegrasConfig(modelMapper);
		tipoDespesaModelToRegrasConfig(modelMapper);	
		
		return modelMapper;
	}
	
	public void regrasModelToRegrasConfig(ModelMapper modelMapper) {
		modelMapper.createTypeMap(RegrasModel.class, Regras.class)
		.addMappings(mapper -> mapper.skip(Regras::setId));
	}
	
	public void tipoDespesaModelToRegrasConfig(ModelMapper modelMapper) {
		modelMapper.createTypeMap(TipoDespesaModel.class, TipoDespesa.class)
		.addMappings(mapper -> mapper.skip(TipoDespesa::setId));
	}
}
