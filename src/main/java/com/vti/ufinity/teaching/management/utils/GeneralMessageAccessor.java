package com.vti.ufinity.teaching.management.utils;

import java.util.Locale;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Service
public class GeneralMessageAccessor {

	private final MessageSource messageSource;

	GeneralMessageAccessor(@Qualifier("generalMessageSource") MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage(Locale locale, String key, Object... parameter) {

		if (Objects.isNull(locale)) {
			return messageSource.getMessage(key, parameter, ProjectConstants.TURKISH_LOCALE);
		}

		return messageSource.getMessage(key, parameter, locale);
	}

}
