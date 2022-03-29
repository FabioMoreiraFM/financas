package com.financas.api.exceptionHandler;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProblemDetails {

	private Integer status;
	private OffsetDateTime timestamp;
	private String title;
	private String detail;
	private String userMessage;
	
}
