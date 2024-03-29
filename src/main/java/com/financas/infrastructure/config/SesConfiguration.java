package com.financas.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

import io.awspring.cloud.ses.SimpleEmailServiceMailSender;

@Configuration
public class SesConfiguration {

	@Value("${cloud.aws.credentials.acess-key}")
	private String acessKey;
	
	@Value("${cloud.aws.credentials.secret-key}")
	private String secretKey;
	
	@Value("${cloud.aws.region.static}")
	private String region;

	@Bean
	public MailSender mailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
		return new SimpleEmailServiceMailSender(amazonSimpleEmailService);
	}
	
	@Bean
	public AmazonSimpleEmailService AmazonSimpleEmailService() {
		BasicAWSCredentials credentials = new BasicAWSCredentials(acessKey, secretKey);
		
		return AmazonSimpleEmailServiceClientBuilder
				.standard()
				.withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.build();
	}
}
