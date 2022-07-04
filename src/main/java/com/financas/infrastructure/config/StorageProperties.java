package com.financas.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cloud.s3")
public class StorageProperties {

	private Credentials credentials;
	private Bucket bucket;
	private Region region;
	
	@Getter
	@Setter
	public static class Credentials {
		private String acessKey;
		private String secretKey;
	}
	
	@Getter
	@Setter
	public static class Bucket {
		private String name;
		private String folder;
	}
	
	@Getter
	@Setter
	public static class Region {
		private String name;
	}
		
}
