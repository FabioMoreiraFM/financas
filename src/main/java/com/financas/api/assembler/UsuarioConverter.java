package com.financas.api.assembler;

import org.springframework.stereotype.Component;

import com.financas.api.model.UsuarioModel;
import com.financas.domain.model.Usuario;

@Component
public class UsuarioConverter extends GenericConverter<UsuarioModel, Usuario>{

}
