package com.financas.api.assembler;

import org.springframework.stereotype.Component;

import com.financas.api.model.input.UsuarioInputModel;
import com.financas.domain.model.Usuario;

@Component
public class UsuarioInputConverter extends GenericConverter<UsuarioInputModel, Usuario>{

}
