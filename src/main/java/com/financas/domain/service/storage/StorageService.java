package com.financas.domain.service.storage;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface StorageService {

	ComprovanteArmazenado recuperar(String nomeArquivo);
	
	void armazenar(Comprovante novoComprovante);
	
	void remover(String nomeArquivo);
	
	default void substituir(String nomeArquivoAntigo, Comprovante novoComprovante) {
		this.armazenar(novoComprovante);
		
		if (nomeArquivoAntigo != null) {
			this.remover(nomeArquivoAntigo);
		}
	}
	
	default String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;
	}
	
	@Builder
	@Getter
	class Comprovante {
		
		private String nome;
		private String contentType;
		private InputStream inputStream;
		
	}
	
	@Builder
	@Getter
	class ComprovanteArmazenado {
		
		private String url;
		
	}
	
}