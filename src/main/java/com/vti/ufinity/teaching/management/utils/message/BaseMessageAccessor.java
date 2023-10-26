package com.vti.ufinity.teaching.management.utils.message;

import java.util.Locale;
import java.util.Objects;

import com.vti.ufinity.teaching.management.utils.constants.ProjectConstants;
import org.springframework.context.MessageSource;

public class BaseMessageAccessor {

    private final MessageSource messageSource;

    protected BaseMessageAccessor(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key, Object... parameter) {

        return getMessage(null, key, parameter);
    }

    public String getMessage(Locale locale, String key, Object... parameter) {

        if (Objects.isNull(locale)) {
            return messageSource.getMessage(key, parameter, ProjectConstants.TURKISH_LOCALE);
        }

        return messageSource.getMessage(key, parameter, locale);
    }
}
