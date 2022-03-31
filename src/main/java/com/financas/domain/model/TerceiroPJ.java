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
@Table(name = "terceiropj")
@PrimaryKeyJoinColumn(name = "idTerceiro")
public class TerceiroPJ extends Terceiro {

	private String cnpj;
	
	@Builder
	public TerceiroPJ(String cnpj, String nome) {
		super(nome);		
		this.cnpj = cnpj;
	}

	@Builder
	public TerceiroPJ() {
		super("");
	}
}
