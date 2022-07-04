package com.financas.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.financas.api.assembler.GenericConverter;
import com.financas.api.model.ComprovanteModel;
import com.financas.api.model.input.ComprovanteInput;
import com.financas.domain.model.Comprovante;
import com.financas.domain.model.ParcelaDespesa;
import com.financas.domain.service.ComprovanteService;
import com.financas.domain.service.DespesaService;
import com.financas.domain.service.ParcelaDespesaService;
import com.financas.domain.service.storage.StorageService;
import com.financas.domain.service.storage.StorageService.ComprovanteArmazenado;

@RestController
@RequestMapping("/despesas/{despesaId}/parcelas/{parcelaDespesaId}/comprovante")
public class ComprovanteStorageController {

	@Autowired
	private DespesaService despesaService;
	
	@Autowired
	private ParcelaDespesaService parcelaDespesaService;
	
	@Autowired
	private ComprovanteService comprovanteService;

	@Autowired
	private StorageService storageService;
	
	@Autowired
	private GenericConverter<ComprovanteModel, Comprovante> comprovanteConverter;
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ComprovanteModel salvar(
			@PathVariable Long despesaId,
			@PathVariable Long parcelaDespesaId,
			ComprovanteInput comprovanteInput
			) throws IOException {
		
		MultipartFile arquivo = comprovanteInput.getArquivo();
		ParcelaDespesa parcelaDespesa = parcelaDespesaService.getParcelaPaga(despesaService.buscar(despesaId), parcelaDespesaId);
		
		Comprovante comprovante = Comprovante.builder()
				.nome(despesaId + "_" + parcelaDespesaId + "_" + arquivo.getOriginalFilename())
				.descricao(comprovanteInput.getDescricao())
				.contentType(arquivo.getContentType())
				.tamanho(arquivo.getSize())
				.parcelaDespesa(parcelaDespesa)
				.build();
		
		return comprovanteConverter.toModel(comprovanteService.salvar(comprovante, arquivo.getInputStream()));
	}
	
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long despesaId,
			@PathVariable Long parcelaDespesaId) {
		
		ParcelaDespesa parcelaDespesa = parcelaDespesaService.getParcelaPaga(despesaService.buscar(despesaId), parcelaDespesaId);
		comprovanteService.excluir(parcelaDespesa.getId());
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ComprovanteModel buscar(@PathVariable Long despesaId,
			@PathVariable Long parcelaDespesaId) {
		ParcelaDespesa parcelaDespesa = parcelaDespesaService.getParcelaPaga(despesaService.buscar(despesaId), parcelaDespesaId);
		
		Comprovante comprovante = comprovanteService.buscar(parcelaDespesa.getId());
		
		return comprovanteConverter.toModel(comprovante);
	}
	
	@GetMapping
	public ResponseEntity<?> emitir(@PathVariable Long despesaId,
			@PathVariable Long parcelaDespesaId) {
		ParcelaDespesa parcelaDespesa = parcelaDespesaService.getParcelaPaga(despesaService.buscar(despesaId), parcelaDespesaId);
		
		Comprovante comprovante = comprovanteService.buscar(parcelaDespesa.getId());
		ComprovanteArmazenado documento = storageService.recuperar(comprovante.getNome());
		
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.header(HttpHeaders.LOCATION, documento.getUrl())
				.build();
	}
}
