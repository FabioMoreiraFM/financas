package com.financas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financas.domain.model.ParcelaDespesa;

@Repository
public interface ParcelaDespesaRepository extends JpaRepository<ParcelaDespesa, Long>{

}
