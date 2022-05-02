package com.financas.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited
@Entity
@Table(name = "agenda")
public class Agenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAgenda")
	private Long id;
	
	@Embedded
	private Email email;
	
	private Integer enviosRealizados;
	
	private LocalDate dtPrimeiroEnvio;
	
	private LocalDate dtUltimoEnvio;	
	
	private Boolean envioFinalizado;

	@OneToOne(mappedBy = "agenda")
	private ParcelaDespesa parcelaDespesa;

	public void finalizarEnvios(Integer qtdMaxEnvios) {
		envioFinalizado = true;
		dtUltimoEnvio = LocalDate.now();
	}
	
	public void atualizarEnvios() {
		enviosRealizados = enviosRealizados + 1;
	}
}