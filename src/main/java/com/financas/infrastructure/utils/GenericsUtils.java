package com.financas.infrastructure.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GenericsUtils {
	
	public static Type getGenericClassType(Class<?> targetClass, int index) {
		Type type = targetClass.getGenericSuperclass(); 
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
}
