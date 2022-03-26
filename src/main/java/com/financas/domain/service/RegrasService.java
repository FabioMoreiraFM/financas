package com.financas.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financas.domain.model.Regras;
import com.financas.domain.repository.RegrasRepository;

@Service
public class RegrasService {
	
	@Autowired
	private RegrasRepository regrasRepository;
		
	public List<Regras> listar() {
		return regrasRepository.listar();
	}
	
	public Regras getRegras(Long regraId) {
		return regrasRepository.getRegra(regraId);
	}
	
	@Transactional
	public void salvar(Regras regras) {
		regrasRepository.save(regras);
	}
	
}
