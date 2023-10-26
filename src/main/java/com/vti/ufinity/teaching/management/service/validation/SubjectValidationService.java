package com.vti.ufinity.teaching.management.service.validation;

import static com.vti.ufinity.teaching.management.utils.constants.MessageCodeConstants.NAME_ALREADY_EXISTS;
import static com.vti.ufinity.teaching.management.utils.constants.MessageCodeConstants.RESOURCE_NOT_FOUND;

import com.vti.ufinity.teaching.management.controller.web.request.SubjectRegisterRequest;
import com.vti.ufinity.teaching.management.exception.RegistrationException;
import com.vti.ufinity.teaching.management.exception.ResourceNotFoundException;
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

    public void validateInsert(SubjectRegisterRequest request) {

        final String name = request.getName();

        checkName(name);
    }

    public void validateUpdate(SubjectRegisterRequest request, Long id) {

        final String name = request.getName();

        checkSubject(id);
        checkNameUsedByOther(name, id);
    }

    private void checkSubject(Long id) {

        boolean existsById = subjectRepository.existsById(id);

        if (!existsById) {
            log.warn("Not found subject with id {}!", id);

            throw new ResourceNotFoundException(exceptionMessageAccessor.getMessage(RESOURCE_NOT_FOUND, id));
        }
    }

    private void checkName(String name) {

        final boolean existByName = subjectRepository.existsByName(name);

        if (existByName) {

            log.warn("Subject {} is exist.", name);

            final String existsName = exceptionMessageAccessor.getMessage(NAME_ALREADY_EXISTS);
            throw new RegistrationException(existsName);
        }
    }

    private void checkNameUsedByOther(String name, Long id) {

        final boolean hasOtherUsed = subjectRepository.existsByNameAndIdNot(name, id);

        if (hasOtherUsed) {
            log.warn("{} is already being used!", name);

            final String existsEmail = exceptionMessageAccessor.getMessage(NAME_ALREADY_EXISTS);
            throw new RegistrationException(existsEmail);
        }
    }
}
