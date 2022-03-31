package com.financas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financas.domain.model.TipoReceita;

@Repository
public interface TipoReceitaRepository extends JpaRepository<TipoReceita, Long>{

}
