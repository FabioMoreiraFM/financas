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

	@ApiModelProperty(example = "404")
	private Integer status;
	
	@ApiModelProperty(example = "2022-05-20T19:18:00.00000Z")
	private OffsetDateTime timestamp;
	
	@ApiModelProperty(example = "Recurso não encontrado")
	private String title;
	
	@ApiModelProperty(example = "O recurso despesas de código 1 não existe.")
	private String detail;
	
	@ApiModelProperty(example = "O recurso despesas de código 1 não existe.")
	private String userMessage;

	@ApiModelProperty("Lista de objetos ou campos que geraram o erro (opcional)")
	private List<Object> objects;
	
	@ApiModel("ObjetoProblema")
	@Getter
	@Builder
	public static class Object {
		
		@ApiModelProperty(example = "descricao")
		private String name;
		
		@ApiModelProperty(example = "A descrição é obrigatória.")
		private String userMessage;
	}
	
}
