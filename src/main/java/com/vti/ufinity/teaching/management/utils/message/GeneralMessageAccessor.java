package com.vti.ufinity.teaching.management.utils.message;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Service
public class GeneralMessageAccessor extends BaseMessageAccessor {

	GeneralMessageAccessor(@Qualifier("generalMessageSource") MessageSource messageSource) {
		super(messageSource);
	}

}
