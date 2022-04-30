package com.financas.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="agenda")
public class AgendaJoin {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "idAgenda", updatable = false, insertable=false)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "idAgenda")
	private Agenda agenda;
	
	@OneToOne
	@JoinColumn(name = "idParcelaDespesa")
	private ParcelaDespesa receita;
}
