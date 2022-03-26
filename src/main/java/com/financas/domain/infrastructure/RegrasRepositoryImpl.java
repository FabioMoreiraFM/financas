package com.financas.domain.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.financas.domain.model.Regras;
import com.financas.domain.repository.RegrasRepository;

@Component
public class RegrasRepositoryImpl implements RegrasRepository {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Regras> listar() {
		return manager.createQuery("from Regras", Regras.class)
				.getResultList();
	}
	
	public Regras getRegra(Long id) {
		return manager.find(Regras.class, id);
	}

	@Override
	public void save(Regras regras) {
		manager.merge(regras);		
	}
}
