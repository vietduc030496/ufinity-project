package com.vti.ufinity.teaching.management.service.validation;

import static com.vti.ufinity.teaching.management.utils.constants.MessageCodeConstants.NAME_ALREADY_EXISTS;

import com.vti.ufinity.teaching.management.controller.web.request.SubjectRegisterRequest;
import com.vti.ufinity.teaching.management.exception.RegistrationException;
import com.vti.ufinity.teaching.management.repository.SubjectRepository;
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
public class SubjectValidationService {

    public final SubjectRepository subjectRepository;

    private final ExceptionMessageAccessor exceptionMessageAccessor;

    public void validate(SubjectRegisterRequest request) {

        final String name = request.getName();

        checkName(name);
    }

    private void checkName(String name) {

        final boolean existByName = subjectRepository.existsByName(name);

        if (existByName) {

            log.warn("Subject {} is exist.", name);

            final String existsName = exceptionMessageAccessor.getMessage(NAME_ALREADY_EXISTS);
            throw new RegistrationException(existsName);
        }
    }
}
