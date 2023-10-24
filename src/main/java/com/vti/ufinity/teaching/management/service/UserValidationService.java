package com.vti.ufinity.teaching.management.service;

import com.vti.ufinity.teaching.management.exception.RegistrationException;
import com.vti.ufinity.teaching.management.repository.UserRepository;
import com.vti.ufinity.teaching.management.security.dto.RegistrationRequest;
import com.vti.ufinity.teaching.management.utils.ExceptionMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationService {

	private static final String EMAIL_ALREADY_EXISTS = "email_already_exists";

	private static final String USERNAME_ALREADY_EXISTS = "username_already_exists";

	private static final String RE_PASSWORD_NOT_SAME = "re_password_not_same";

	private final UserRepository userRepository;

	private final ExceptionMessageAccessor exceptionMessageAccessor;

	public void validateUser(RegistrationRequest registrationRequest) {

		final String email = registrationRequest.getEmail();
		final String username = registrationRequest.getUsername();
		final String password = registrationRequest.getPassword();
		final String rePassword = registrationRequest.getRePassword();

		checkEmail(email);
		checkUsername(username);
		checkPassword(password, rePassword);

	}

	private void checkUsername(String username) {

		final boolean existsByUsername = userRepository.existsByUsername(username);

		if (existsByUsername) {

			log.warn("{} is already being used!", username);

			final String existsUsername = exceptionMessageAccessor.getMessage(null, USERNAME_ALREADY_EXISTS);
			throw new RegistrationException(existsUsername);
		}

	}

	private void checkEmail(String email) {

		final boolean existsByEmail = userRepository.existsByEmail(email);

		if (existsByEmail) {

			log.warn("{} is already being used!", email);

			final String existsEmail = exceptionMessageAccessor.getMessage(null, EMAIL_ALREADY_EXISTS);
			throw new RegistrationException(existsEmail);
		}
	}

	private void checkPassword(String password, String rePassword) {

		if (!StringUtils.equals(password, rePassword)) {

			log.warn("password and re password not same!");

			throw new RegistrationException(exceptionMessageAccessor.getMessage(null, RE_PASSWORD_NOT_SAME));
		}
	}


}
