package com.financas.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.financas.infrastructure.utils.GenericsUtils;

@Component
public class GenericConverter<X, T> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	private Class<X> xType;
	private Class<T> tType;
	
	public GenericConverter(Class<X> xType, Class<T> tType) {
		this.xType = xType;
		this.tType = tType;
	}
	
	@SuppressWarnings("unchecked")
	public GenericConverter() {
		this.xType = (Class<X>) GenericsUtils.getGenericClassType(getClass(), 0);
		this.tType = (Class<T>) GenericsUtils.getGenericClassType(getClass(), 1);
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
	
	public T toDomainObject(X regrasModel) {
		return modelMapper.map(regrasModel, tType);
	}
}
