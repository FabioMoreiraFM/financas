package com.financas.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Data;

@Data
@Entity
@Audited
@Table(name = "receita")
public class Receita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idReceita")
	private Long id;
	
	private String descricao;
	
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name = "fonte")
	private Terceiro fonte;
	
	private LocalDate dtInicio;
	
	private LocalDate dtFim;

	@ManyToOne
	@JoinColumn(name = "idTipoReceita")
	private TipoReceita tipoReceita;	

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;	
	
//	private Comprovante recibo;
}
