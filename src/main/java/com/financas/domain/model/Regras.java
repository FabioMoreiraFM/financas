package com.financas.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Audited
@Table(name = "regras")
public class Regras {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRegras")
	private Long id;

	private Integer qtdMaxEnvioAgendamento;

	private Double fatorDespesaReceitaEnvioEmail;

	private Boolean enviarEmailDespesasAtrasadas;	
}
