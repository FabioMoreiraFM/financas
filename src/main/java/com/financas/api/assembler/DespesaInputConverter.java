package com.financas.api.assembler;

import org.springframework.stereotype.Component;

import com.financas.api.model.input.DespesaInputModel;
import com.financas.domain.model.Despesa;

@Component
public class DespesaInputConverter extends GenericConverter<DespesaInputModel, Despesa>{

}
