package com.financas.infrastructure.openapi;

import static springfox.bean.validators.plugins.Validators.annotationFromBean;
import static springfox.bean.validators.plugins.Validators.annotationFromField;

import javax.validation.constraints.NotBlank;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Optional;

import springfox.bean.validators.plugins.Validators;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

@Component
@Order(Validators.BEAN_VALIDATOR_PLUGIN_ORDER)
public class NotBlankAnnotationPlugin implements ModelPropertyBuilderPlugin {

  /**
   * support all documentationTypes
   */
  @Override
  public boolean supports(DocumentationType delimiter) {
    // we simply support all documentationTypes!
    return true;
  }

  /**
   * read NotBlank annotation
   */
  @Override
  public void apply(ModelPropertyContext context) {
    Optional<NotBlank> notNull = extractAnnotation(context);
    if (notNull.isPresent()) {
      context.getBuilder().required(notNull.isPresent());
    }
  }

  @VisibleForTesting
  Optional<NotBlank> extractAnnotation(ModelPropertyContext context) {
    return annotationFromBean(context, NotBlank.class).or(annotationFromField(context, NotBlank.class));
  }
}
