package com.financas.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Email {

	@Column(name = "to_email")
	private String to;
	
	@Column(name = "from_email")
	private String from;

	private String titulo;
	
	private String corpoEmail;
}
