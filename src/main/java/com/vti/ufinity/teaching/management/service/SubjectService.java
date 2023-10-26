package com.vti.ufinity.teaching.management.service;

import static com.vti.ufinity.teaching.management.utils.constants.MessageCodeConstants.RESOURCE_NOT_FOUND;
import static com.vti.ufinity.teaching.management.utils.constants.MessageCodeConstants.NAME_ALREADY_EXISTS;

import java.util.List;
import java.util.Optional;

import com.vti.ufinity.teaching.management.controller.web.request.SubjectRegisterRequest;
import com.vti.ufinity.teaching.management.exception.RegistrationException;
import com.vti.ufinity.teaching.management.exception.ResourceNotFoundException;
import com.vti.ufinity.teaching.management.model.Subject;
import com.vti.ufinity.teaching.management.model.dto.SubjectDTO;
import com.vti.ufinity.teaching.management.model.mapper.SubjectMapper;
import com.vti.ufinity.teaching.management.repository.ClassRepository;
import com.vti.ufinity.teaching.management.repository.SubjectRepository;
import com.vti.ufinity.teaching.management.service.validation.SubjectValidationService;
import com.vti.ufinity.teaching.management.utils.message.ExceptionMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SubjectService implements CrudService<SubjectDTO> {

    private final SubjectRepository subjectRepository;

    private final ClassRepository classRepository;

    private final SubjectValidationService validationService;

    private final ExceptionMessageAccessor exceptionMessageAccessor;

    @Override
    public List<SubjectDTO> findAll() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream().map(SubjectMapper.INSTANCE::entityToDto).toList();
    }

    @Override
    public Optional<SubjectDTO> findById(Long id) {
        Optional<Subject> subjectOptional = subjectRepository.findById(id);
        return subjectOptional.map(SubjectMapper.INSTANCE::entityToDto);
    }

    @Transactional
    public SubjectDTO save(SubjectRegisterRequest body) {

        validationService.validate(body);

        final Subject newSubject = new Subject();
        newSubject.setName(body.getName());

        return SubjectMapper.INSTANCE.entityToDto(subjectRepository.save(newSubject));
    }

    @Transactional
    public SubjectDTO update(Long id, SubjectRegisterRequest body) {

        final String name = body.getName();

        Optional<Subject> subjectOptional = subjectRepository.findById(id);
        if (subjectOptional.isEmpty()) {
            log.warn("Not found subject with id {}!", id);

            throw new ResourceNotFoundException(exceptionMessageAccessor.getMessage(RESOURCE_NOT_FOUND, id));
        }

        boolean hasOtherUsed = subjectRepository.existsByNameAndIdNot(name, id);
        if (hasOtherUsed) {
            log.warn("{} is already being used!", name);

            final String existsEmail = exceptionMessageAccessor.getMessage(NAME_ALREADY_EXISTS);
            throw new RegistrationException(existsEmail);
        }

        final Subject subject = subjectOptional.get();
        subject.setName(body.getName());

        return SubjectMapper.INSTANCE.entityToDto(subjectRepository.save(subject));
    }

    @Override
    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }

}
