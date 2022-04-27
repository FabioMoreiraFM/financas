package com.financas.api.assembler;

import org.springframework.stereotype.Component;

import com.financas.api.model.input.ReceitaInputModel;
import com.financas.domain.model.Receita;

@Component
public class ReceitaInputConverter extends GenericConverter<ReceitaInputModel, Receita>{

}
