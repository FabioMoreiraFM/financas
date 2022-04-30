package com.financas.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.envers.Audited;

import lombok.Data;

@Data
@Entity
@Audited
@Table(name = "tipo_despesa")
public class TipoDespesa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTipoDespesa")
	private Long id;
	
	@NotBlank
	private String descricao;

}
