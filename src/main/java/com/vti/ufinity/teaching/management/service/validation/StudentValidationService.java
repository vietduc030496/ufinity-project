package com.vti.ufinity.teaching.management.service.validation;

import static com.vti.ufinity.teaching.management.utils.constants.MessageCodeConstants.EMAIL_ALREADY_EXISTS;

import com.vti.ufinity.teaching.management.controller.web.request.StudentRegisterRequest;
import com.vti.ufinity.teaching.management.exception.RegistrationException;
import com.vti.ufinity.teaching.management.repository.StudentRepository;
import com.vti.ufinity.teaching.management.utils.message.ExceptionMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StudentValidationService {

    public final StudentRepository studentRepository;

    private final ExceptionMessageAccessor exceptionMessageAccessor;

    public void validate(StudentRegisterRequest request) {

        final String username = request.getEmail();

        checkEmail(username);

    }

    private void checkEmail(String email) {

        final boolean existsByEmail = studentRepository.existsByEmail(email);

        if (existsByEmail) {

            log.warn("{} is already being used!", email);

            final String existsEmail = exceptionMessageAccessor.getMessage(EMAIL_ALREADY_EXISTS);
            throw new RegistrationException(existsEmail);
        }
    }
}
