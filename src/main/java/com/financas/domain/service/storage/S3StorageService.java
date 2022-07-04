package com.financas.domain.service.storage;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.financas.domain.exception.StorageException;
import com.financas.infrastructure.config.StorageProperties;

@Service
public class S3StorageService implements StorageService{

	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private StorageProperties storageProperties;
	
	@Override
	public ComprovanteArmazenado recuperar(String nomeArquivo) {
		String caminhoArquivo = getCaminhoArquivo(nomeArquivo);
		
		URL url = amazonS3.getUrl(storageProperties.getBucket().getName(), caminhoArquivo);
		
		return ComprovanteArmazenado.builder().url(url.toString()).build();
	}
	
	@Override
	public void armazenar(Comprovante novoComprovante) {
		try {
			String caminhoArquivo = getCaminhoArquivo(novoComprovante.getNome());
	
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(novoComprovante.getContentType());
			
			PutObjectRequest pubObjectRequest = new PutObjectRequest(
						storageProperties.getBucket().getName(),
						caminhoArquivo,
						novoComprovante.getInputStream(),
						objectMetadata
					)
				.withCannedAcl(CannedAccessControlList.PublicRead);
			
			amazonS3.putObject(pubObjectRequest);
		} catch (Exception e) {
			throw new StorageException("Erro ao armazenar arquivo na S3", e);
		}
	}
	
	@Override
	public void remover(String nomeArquivo) {
		try {
	        String caminhoArquivo = getCaminhoArquivo(nomeArquivo);
	
	        var deleteObjectRequest = new DeleteObjectRequest(
	        		storageProperties.getBucket().getName(), caminhoArquivo);
	
	        amazonS3.deleteObject(deleteObjectRequest);
		} catch (Exception e) {
			throw new StorageException("Erro ao excluir arquivo da S3", e);
		}
	}
	
	private String getCaminhoArquivo(String nomeArquivo) {
		return String.format("%s/%s", storageProperties.getBucket().getFolder(), nomeArquivo);
	}

}
