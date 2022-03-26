package com.financas.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.financas.domain.model.Regras;

@Repository
public interface RegrasRepository {

	public List<Regras> listar();

	public Regras getRegra(Long id);
	
	public void save(Regras regras);
}
