package com.vti.ufinity.teaching.management.service.validation;

import static com.vti.ufinity.teaching.management.utils.constants.MessageCodeConstants.RE_PASSWORD_NOT_SAME;
import static com.vti.ufinity.teaching.management.utils.constants.MessageCodeConstants.USERNAME_ALREADY_EXISTS;

import com.vti.ufinity.teaching.management.exception.RegistrationException;
import com.vti.ufinity.teaching.management.repository.UserRepository;
import com.vti.ufinity.teaching.management.security.dto.RegistrationRequest;
import com.vti.ufinity.teaching.management.utils.message.ExceptionMessageAccessor;
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

	private final UserRepository userRepository;

	private final ExceptionMessageAccessor exceptionMessageAccessor;

	public void validateUser(RegistrationRequest registrationRequest) {

		final String username = registrationRequest.getUsername();
		final String password = registrationRequest.getPassword();
		final String rePassword = registrationRequest.getRePassword();

		checkUsername(username);
		checkPassword(password, rePassword);

	}

	private void checkUsername(String username) {

		final boolean existsByUsername = userRepository.existsByUsername(username);

		if (existsByUsername) {

			log.warn("{} is already being used!", username);

			final String existsUsername = exceptionMessageAccessor.getMessage(USERNAME_ALREADY_EXISTS);
			throw new RegistrationException(existsUsername);
		}

	}

	private void checkPassword(String password, String rePassword) {

		if (!StringUtils.equals(password, rePassword)) {

			log.warn("password and re password not same!");

			throw new RegistrationException(exceptionMessageAccessor.getMessage(RE_PASSWORD_NOT_SAME));
		}
	}


}
