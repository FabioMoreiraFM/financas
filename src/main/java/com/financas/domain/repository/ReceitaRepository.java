package com.financas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financas.domain.model.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

}
