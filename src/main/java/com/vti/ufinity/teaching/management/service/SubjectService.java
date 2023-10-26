package com.vti.ufinity.teaching.management.service;

import java.util.List;
import java.util.Optional;

import com.vti.ufinity.teaching.management.controller.web.request.SubjectRegisterRequest;
import com.vti.ufinity.teaching.management.model.Subject;
import com.vti.ufinity.teaching.management.model.dto.SubjectDTO;
import com.vti.ufinity.teaching.management.model.mapper.SubjectMapper;
import com.vti.ufinity.teaching.management.repository.SubjectRepository;
import com.vti.ufinity.teaching.management.service.validation.SubjectValidationService;
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

    private final SubjectValidationService validationService;

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

        validationService.validateInsert(body);

        final Subject newSubject = new Subject();
        newSubject.setName(body.getName());

        return SubjectMapper.INSTANCE.entityToDto(subjectRepository.save(newSubject));
    }

    @Transactional
    public SubjectDTO update(Long id, SubjectRegisterRequest body) {

        validationService.validateUpdate(body, id);

        final Subject subject = subjectRepository.getReferenceById(id);
        subject.setName(body.getName());

        return SubjectMapper.INSTANCE.entityToDto(subjectRepository.save(subject));
    }

    @Override
    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }

}
