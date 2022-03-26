package com.financas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financas.domain.model.Regras;

@Repository
public interface RegrasRepository extends JpaRepository<Regras, Long>{

	
}
