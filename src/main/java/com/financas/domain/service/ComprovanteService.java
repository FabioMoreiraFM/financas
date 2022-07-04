package com.financas.domain.service;

import java.io.InputStream;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financas.domain.exception.EntidadeNaoEncontradaException;
import com.financas.domain.model.Comprovante;
import com.financas.domain.repository.ComprovanteRepository;
import com.financas.domain.service.storage.StorageService;

@Service
public class ComprovanteService {

	@Autowired
	private StorageService storageService;
	
	@Autowired
	private ComprovanteRepository comprovanteRepository;
	
	public Comprovante salvar(Comprovante comprovante, InputStream dados) {
		Optional<Comprovante> comprovanteExistente = comprovanteRepository
				.findComprovanteByParcelaDespesaId(comprovante.getParcelaDespesa().getId());
		String nomeComprovanteExistente = null;
		
		if (comprovanteExistente.isPresent()) {
			nomeComprovanteExistente = comprovanteExistente.get().getNome();
			comprovanteRepository.deleteByNome(nomeComprovanteExistente);
		}
		
		comprovante = comprovanteRepository.save(comprovante);
		comprovanteRepository.flush();
		
		StorageService.Comprovante comprovanteStorage = StorageService.Comprovante.builder()
				.nome(comprovante.getNome())
				.contentType(comprovante.getContentType())
				.inputStream(dados)
				.build();
		
		storageService.substituir(nomeComprovanteExistente, comprovanteStorage);
		
		return comprovante;
	}
	
	public Comprovante buscar(Long parcelaDespesaId) {
		return comprovanteRepository.findComprovanteByParcelaDespesaId(parcelaDespesaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("O comprovante da parcela de id {} não foi encontrado", parcelaDespesaId)));
	}
	
	@Transactional
	public void excluir(Long parcelaDespesaId) {
		Comprovante comprovante = buscar(parcelaDespesaId);
		
		comprovanteRepository.delete(comprovante);
		comprovanteRepository.flush();
		
		storageService.remover(comprovante.getNome());
	}
}
