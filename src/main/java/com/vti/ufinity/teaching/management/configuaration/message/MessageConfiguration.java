package com.vti.ufinity.teaching.management.configuaration.message;

import java.nio.charset.StandardCharsets;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Configuration
public class MessageConfiguration {

	final String DEFAULT_ENCODING = StandardCharsets.UTF_8.toString();

	@Bean
	public MessageSource generalMessageSource() {

		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages/general/GeneralMessages");

		messageSource.setDefaultEncoding(DEFAULT_ENCODING);

		return messageSource;
	}

	@Bean
	public MessageSource exceptionMessageSource() {

		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages/exception/ExceptionMessages");
		messageSource.setDefaultEncoding(DEFAULT_ENCODING);

		return messageSource;
	}

	@Bean
	public MessageSource validationMessageSource() {

		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messages/validation/ValidationMessages");
		messageSource.setDefaultEncoding(DEFAULT_ENCODING);

		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {

		final LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(validationMessageSource());

		return bean;
	}

}
