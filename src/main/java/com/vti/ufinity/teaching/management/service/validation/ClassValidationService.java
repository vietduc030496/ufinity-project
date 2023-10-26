package com.vti.ufinity.teaching.management.service.validation;

import static com.vti.ufinity.teaching.management.utils.constants.MessageCodeConstants.NAME_ALREADY_EXISTS;
import static com.vti.ufinity.teaching.management.utils.constants.MessageCodeConstants.RESOURCE_NOT_FOUND;

import com.vti.ufinity.teaching.management.controller.web.request.ClassRegisterRequest;
import com.vti.ufinity.teaching.management.exception.RegistrationException;
import com.vti.ufinity.teaching.management.repository.ClassRepository;
import com.vti.ufinity.teaching.management.repository.SubjectRepository;
import com.vti.ufinity.teaching.management.repository.TeacherRepository;
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
public class ClassValidationService {

    private final ClassRepository classRepository;

    private final SubjectRepository subjectRepository;

    private final TeacherRepository teacherRepository;

    private final ExceptionMessageAccessor exceptionMessageAccessor;

    public void validateInsert(ClassRegisterRequest request) {

        final String name = request.getName();
        final Long subjectId = request.getSubjectId();
        final Long teacherId = request.getTeacherId();

        checkName(name);
        checkSubject(subjectId);
        checkTeacher(teacherId);

    }

    public void validateUpdate(ClassRegisterRequest request, Long id) {

        final String name = request.getName();
        final Long subjectId = request.getSubjectId();
        final Long teacherId = request.getTeacherId();

        checkNameUsedByOther(name, id);
        checkClass(id);
        checkSubject(subjectId);
        checkTeacher(teacherId);

    }

    private void checkName(String name) {

        final boolean hasUsed = classRepository.existsByName(name);

        if (hasUsed) {

            log.warn("Class name {} is exists.", name);

            final String message = exceptionMessageAccessor.getMessage(NAME_ALREADY_EXISTS);
            throw new RegistrationException(message);
        }

    }

    private void checkNameUsedByOther(String name, Long id) {

        final boolean hasUsed = classRepository.existsByNameAndIdNot(name, id);

        if (hasUsed) {

            log.warn("Class name {} is used by other class.", name);

            final String message = exceptionMessageAccessor.getMessage(NAME_ALREADY_EXISTS);
            throw new RegistrationException(message);
        }
    }

    private void checkClass(Long id) {

        boolean existsById = classRepository.existsById(id);

        if (!existsById) {

            log.warn("Class with id: {} is not exists.", id);

            final String message = exceptionMessageAccessor.getMessage(RESOURCE_NOT_FOUND);
            throw new RegistrationException(message);
        }
    }

    private void checkSubject(Long id) {

        final boolean existsById = subjectRepository.existsById(id);

        if (!existsById) {

            log.warn("Subject with id: {} is not exists.", id);

            final String message = exceptionMessageAccessor.getMessage(RESOURCE_NOT_FOUND);
            throw new RegistrationException(message);
        }
    }

    private void checkTeacher(Long id) {

        final boolean existsById = teacherRepository.existsById(id);

        if (!existsById) {

            log.warn("Subject with id: {} is not exists.", id);

            final String message = exceptionMessageAccessor.getMessage(RESOURCE_NOT_FOUND);
            throw new RegistrationException(message);
        }
    }

}
