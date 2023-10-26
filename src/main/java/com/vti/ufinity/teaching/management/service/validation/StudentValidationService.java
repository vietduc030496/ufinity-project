package com.vti.ufinity.teaching.management.service.validation;

import static com.vti.ufinity.teaching.management.utils.constants.MessageCodeConstants.EMAIL_ALREADY_EXISTS;
import static com.vti.ufinity.teaching.management.utils.constants.MessageCodeConstants.RESOURCE_NOT_FOUND;

import com.vti.ufinity.teaching.management.controller.web.request.StudentRegisterRequest;
import com.vti.ufinity.teaching.management.exception.RegistrationException;
import com.vti.ufinity.teaching.management.exception.ResourceNotFoundException;
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

    public void validateInsert(StudentRegisterRequest request) {

        final String email = request.getEmail();

        checkEmail(email);
    }

    public void validateUpdate(StudentRegisterRequest request, Long id) {

        final String email = request.getEmail();

        checkStudent(id);
        checkEmailUsedByOther(email, id);
    }

    private void checkStudent(Long id) {

        final boolean existsById = studentRepository.existsById(id);

        if (!existsById) {
            log.warn("Not found student with id {}!", id);

            throw new ResourceNotFoundException(exceptionMessageAccessor.getMessage(RESOURCE_NOT_FOUND, id));
        }
    }

    private void checkEmail(String email) {

        final boolean existsByEmail = studentRepository.existsByEmail(email);

        if (existsByEmail) {

            log.warn("{} is already being used!", email);

            final String existsEmail = exceptionMessageAccessor.getMessage(EMAIL_ALREADY_EXISTS);
            throw new RegistrationException(existsEmail);
        }
    }

    private void checkEmailUsedByOther(String email, Long id) {

        boolean emailUsedByOtherUser = studentRepository.existsByEmailAndIdNot(email, id);

        if (emailUsedByOtherUser) {
            log.warn("{} is already being used!", email);

            final String existsEmail = exceptionMessageAccessor.getMessage(EMAIL_ALREADY_EXISTS);
            throw new RegistrationException(existsEmail);
        }
    }
}
