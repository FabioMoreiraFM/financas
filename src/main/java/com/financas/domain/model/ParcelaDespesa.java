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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Audited
@Table(name = "parcela_despesa")
public class ParcelaDespesa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idParcelaDespesa")
	private Long id;
	
	private BigDecimal valor;
	
	@Column(name = "nParcela")
	private Integer numeroParcela;
	
	private LocalDate dtVencimento;
	
	private LocalDate dtPagamento;
	
	@ManyToOne
	@JoinColumn(name = "idDespesa")
	private Despesa despesa;
	
	@OneToOne
	@JoinColumn(name = "idAgenda")
	private Agenda agenda;
}
