package com.financas.api.infrastructure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.financas.api.model.RegrasModel;
import com.financas.api.model.TerceiroModel;
import com.financas.api.model.TipoDespesaModel;
import com.financas.api.model.TipoReceitaModel;
import com.financas.api.model.UsuarioModel;
import com.financas.domain.model.Regras;
import com.financas.domain.model.Terceiro;
import com.financas.domain.model.TipoDespesa;
import com.financas.domain.model.TipoReceita;
import com.financas.domain.model.Usuario;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		regrasModelToRegrasConfig(modelMapper);
		tipoDespesaModelToTipoDespesaConfig(modelMapper);	
		tipoReceitaModelToTipoReceitaConfig(modelMapper);
		terceiroModelToTerceiroConfig(modelMapper);
		usuarioModelToUsuarioConfig(modelMapper);
		
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
	
	public void terceiroModelToTerceiroConfig(ModelMapper modelMapper) {
		modelMapper.createTypeMap(TerceiroModel.class, Terceiro.class)
		.addMappings(mapper -> mapper.skip(Terceiro::setId));
	}
	
	public void usuarioModelToUsuarioConfig(ModelMapper modelMapper) {
		modelMapper.createTypeMap(UsuarioModel.class, Usuario.class)
		.addMappings(mapper -> mapper.skip(Usuario::setId));
	}
	
}
