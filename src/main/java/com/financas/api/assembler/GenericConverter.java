package com.financas.api.assembler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericConverter<X, T> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	private Class<X> xType;
	
	@SuppressWarnings("unchecked")
	public GenericConverter() {
		this.xType = (Class<X>) getGenericClassType(0);
	}
	
	private Type getGenericClassType(int index) {
		Type type = getClass().getGenericSuperclass(); 
		while(!(type instanceof ParameterizedType)) { 
			if (type == null) { 
				return null; 
			}
			
			if (type instanceof ParameterizedType) {
				type = ((Class<?>) ((ParameterizedType) type).getRawType()).getGenericSuperclass();
			} else {
				type = ((Class<?>) type).getGenericSuperclass();
			} 
		}
		
		return ((ParameterizedType) type).getActualTypeArguments()[index];
	}
 
	public void copyToDomainObject(X regrasModel, T regras) {				
		modelMapper.map(regrasModel, regras);
	}
	
	public X toModel(T regras) {
		return modelMapper.map(regras, xType); 
	}
	
	public List<X> toCollectionModel(List<T> regras) {
		return regras.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
}
