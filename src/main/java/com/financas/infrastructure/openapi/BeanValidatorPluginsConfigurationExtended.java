package com.financas.infrastructure.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanValidatorPluginsConfigurationExtended {

  @Bean
  public NotBlankAnnotationPlugin notBlankPlugin() {
    return new NotBlankAnnotationPlugin();
  }

	
}
