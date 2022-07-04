package com.financas.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comprovante")
public class Comprovante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idComprovante")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "idParcelaDespesa")
	private ParcelaDespesa parcelaDespesa;

	private String nome;
	
	private String descricao;
	
	private String contentType;
	
	private Long tamanho;
}
