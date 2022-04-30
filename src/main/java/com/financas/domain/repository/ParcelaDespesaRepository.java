package com.financas.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financas.domain.model.ParcelaDespesa;

@Repository
public interface ParcelaDespesaRepository extends JpaRepository<ParcelaDespesa, Long>{

	public List<ParcelaDespesa> findByAgendaIsNullAndDtVencimentoBetween(LocalDate dtInicial, LocalDate dtFinal); 
	
}
