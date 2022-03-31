package com.financas.api.infrastructure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.financas.api.model.RegrasModel;
import com.financas.api.model.TerceiroPFModel;
import com.financas.api.model.TerceiroPJModel;
import com.financas.api.model.TipoDespesaModel;
import com.financas.api.model.TipoReceitaModel;
import com.financas.domain.model.Regras;
import com.financas.domain.model.TerceiroPF;
import com.financas.domain.model.TerceiroPJ;
import com.financas.domain.model.TipoDespesa;
import com.financas.domain.model.TipoReceita;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		regrasModelToRegrasConfig(modelMapper);
		tipoDespesaModelToTipoDespesaConfig(modelMapper);	
		tipoReceitaModelToTipoReceitaConfig(modelMapper);
		terceiroPFModelToTerceiroPFConfig(modelMapper);
		terceiroPJModelToTerceiroPJConfig(modelMapper);
		
		return modelMapper;
	}
	
	public void regrasModelToRegrasConfig(ModelMapper modelMapper) {
		modelMapper.createTypeMap(RegrasModel.class, Regras.class)
		.addMappings(mapper -> mapper.skip(Regras::setId));
	}
	
	public void tipoDespesaModelToTipoDespesaConfig(ModelMapper modelMapper) {
		modelMapper.createTypeMap(TipoDespesaModel.class, TipoDespesa.class)
		.addMappings(mapper -> mapper.skip(TipoDespesa::setId));
	}
	
	public void tipoReceitaModelToTipoReceitaConfig(ModelMapper modelMapper) {
		modelMapper.createTypeMap(TipoReceitaModel.class, TipoReceita.class)
		.addMappings(mapper -> mapper.skip(TipoReceita::setId));
	}
	
	public void terceiroPFModelToTerceiroPFConfig(ModelMapper modelMapper) {
		modelMapper.createTypeMap(TerceiroPFModel.class, TerceiroPF.class)
		.addMappings(mapper -> mapper.skip(TerceiroPF::setId));
	}
	
	public void terceiroPJModelToTerceiroPJConfig(ModelMapper modelMapper) {
		modelMapper.createTypeMap(TerceiroPJModel.class, TerceiroPJ.class)
		.addMappings(mapper -> mapper.skip(TerceiroPJ::setId));
	}
	
}
