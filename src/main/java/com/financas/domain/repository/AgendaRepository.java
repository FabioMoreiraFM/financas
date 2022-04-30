package com.financas.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financas.domain.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

	public List<Agenda> findBydtPrimeiroEnvioAndEnvioFinalizado(LocalDate dtEnvio, Boolean envioFinalizado);
	
}
