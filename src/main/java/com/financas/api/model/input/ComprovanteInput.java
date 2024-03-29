package com.financas.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComprovanteInput {

	@NotNull
	private MultipartFile arquivo;

	@NotBlank
	private String descricao;

}