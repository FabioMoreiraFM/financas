package com.financas.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.financas.domain.listener.GerarParcelasDespesaListener;

import lombok.Data;

@Data
@EntityListeners({GerarParcelasDespesaListener.class})
@Entity
@Audited
@Table(name = "despesa")
public class Despesa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDespesa")
	private Long id;
	
	private String descricao;
	
	private BigDecimal valorTotal;
	
	private BigDecimal valorParcela;
	
	private Integer totalParcelas;
	
	@ManyToOne
	@JoinColumn(name =  "credor")
	private Terceiro credor;
	
	private LocalDate mesAnoInicioPgto;
	
	private LocalDate dtQuitacao;
	
	private Integer diaVencimentoParcela;
	
	@ManyToOne
	@JoinColumn(name =  "idTipoDespesa")
	private TipoDespesa tipoDespesa;
	
	@ManyToOne
	@JoinColumn(name =  "idUsuario")
	private Usuario usuario;
	
//	private Comprovante comprovantePgto;
	
	@OneToMany(mappedBy = "despesa", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<ParcelaDespesa> parcelas;
	
	@PrePersist
	public void aoSalvar() {
		valorTotal = valorParcela.multiply(new BigDecimal(totalParcelas));
	}
}
