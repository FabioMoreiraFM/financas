package com.financas.api.infrastructure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.financas.api.model.RegrasModel;
import com.financas.domain.model.Regras;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		regrasModelToRegrasConfig(modelMapper);
			
		return modelMapper;
	}
	
	public void regrasModelToRegrasConfig(ModelMapper modelMapper) {
		modelMapper.createTypeMap(RegrasModel.class, Regras.class)
		.addMappings(mapper -> mapper.skip(Regras::setId));
	}
	
}
