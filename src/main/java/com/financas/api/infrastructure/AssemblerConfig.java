package com.financas.api.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.financas.api.assembler.GenericConverter;
import com.financas.api.model.DespesaModel;
import com.financas.api.model.ReceitaModel;
import com.financas.api.model.RegrasModel;
import com.financas.api.model.TerceiroModel;
import com.financas.api.model.TipoDespesaModel;
import com.financas.api.model.TipoReceitaModel;
import com.financas.api.model.UsuarioModel;
import com.financas.api.model.input.DespesaInputModel;
import com.financas.api.model.input.ReceitaInputModel;
import com.financas.api.model.input.RegrasInputModel;
import com.financas.api.model.input.TerceiroInputModel;
import com.financas.api.model.input.TipoDespesaInputModel;
import com.financas.api.model.input.TipoReceitaInputModel;
import com.financas.api.model.input.UsuarioInputComSenhaModel;
import com.financas.domain.model.Despesa;
import com.financas.domain.model.Receita;
import com.financas.domain.model.Regras;
import com.financas.domain.model.Terceiro;
import com.financas.domain.model.TipoDespesa;
import com.financas.domain.model.TipoReceita;
import com.financas.domain.model.Usuario;

@Configuration
public class AssemblerConfig {

	@Bean
	public GenericConverter<TipoDespesaModel, TipoDespesa> tipoDespesaConverter() {
		return new GenericConverter<TipoDespesaModel, TipoDespesa>
			(TipoDespesaModel.class, TipoDespesa.class);
	}
	
	@Bean
	public GenericConverter<TipoDespesaInputModel, TipoDespesa> tipoDespesaInputConverter() {
		return new GenericConverter<TipoDespesaInputModel, TipoDespesa>
			(TipoDespesaInputModel.class, TipoDespesa.class);
	}
	
	@Bean
	public GenericConverter<DespesaModel, Despesa> despesaConverter() {
		return new GenericConverter<DespesaModel, Despesa>
			(DespesaModel.class, Despesa.class);
	}
	
	@Bean
	public GenericConverter<DespesaInputModel, Despesa> despesaInputConverter() {
		return new GenericConverter<DespesaInputModel, Despesa>
			(DespesaInputModel.class, Despesa.class);
	}
	
	@Bean
	public GenericConverter<RegrasModel, Regras> regrasConverter() {
		return new GenericConverter<RegrasModel, Regras>
			(RegrasModel.class, Regras.class);
	}
	
	@Bean
	public GenericConverter<RegrasInputModel, Regras> regrasInputConverter() {
		return new GenericConverter<RegrasInputModel, Regras>
			(RegrasInputModel.class, Regras.class);
	}
	
	@Bean
	public GenericConverter<TerceiroModel, Terceiro> terceiroConverter() {
		return new GenericConverter<TerceiroModel, Terceiro>
			(TerceiroModel.class, Terceiro.class);
	}
	
	@Bean
	public GenericConverter<TerceiroInputModel, Terceiro> terceiroInputConverter() {
		return new GenericConverter<TerceiroInputModel, Terceiro>
			(TerceiroInputModel.class, Terceiro.class);
	}
	
	@Bean
	public GenericConverter<ReceitaModel, Receita> receitaConverter() {
		return new GenericConverter<ReceitaModel, Receita>
			(ReceitaModel.class, Receita.class);
	}
	
	@Bean
	public GenericConverter<ReceitaInputModel, Receita> receitaInputConverter() {
		return new GenericConverter<ReceitaInputModel, Receita>
			(ReceitaInputModel.class, Receita.class);
	}
	
	@Bean
	public GenericConverter<TipoReceitaModel, TipoReceita> tipoReceitaConverter() {
		return new GenericConverter<TipoReceitaModel, TipoReceita>
			(TipoReceitaModel.class, TipoReceita.class);
	}
	
	@Bean
	public GenericConverter<TipoReceitaInputModel, TipoReceita> tipoReceitaInputConverter() {
		return new GenericConverter<TipoReceitaInputModel, TipoReceita>
			(TipoReceitaInputModel.class, TipoReceita.class);
	}
	
	@Bean
	public GenericConverter<UsuarioModel, Usuario> usuarioConverter() {
		return new GenericConverter<UsuarioModel, Usuario>
			(UsuarioModel.class, Usuario.class);
	}
	
	@Bean
	public GenericConverter<UsuarioInputComSenhaModel, TipoReceita> usuarioInputConverter() {
		return new GenericConverter<UsuarioInputComSenhaModel, TipoReceita>
			(UsuarioInputComSenhaModel.class, TipoReceita.class);
	}
	
}
