package com.financas.domain.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "terceiropf")
@PrimaryKeyJoinColumn(name = "idTerceiro")
public class TerceiroPF extends Terceiro {

	private String cpf;
	
	@Builder
	public TerceiroPF(String cpf, String nome) {
		super(nome);	
		this.cpf = cpf;
	}

}
