package com.financas.api.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema")
@Getter
@Builder
public class ProblemDetails {

	private Integer status;
	private OffsetDateTime timestamp;
	private String title;
	private String detail;
	private String userMessage;

	@ApiModelProperty("Lista de objetos ou campos que geraram o erro (opcional)")
	private List<Object> objects;
	
	@ApiModel("ObjetoProblema")
	@Getter
	@Builder
	public static class Object {
		
		private String name;
		
		private String userMessage;
	}
	
}
