package com.vti.ufinity.teaching.management.service;

import java.util.List;
import java.util.Optional;

import com.vti.ufinity.teaching.management.controller.web.request.TeacherRegisterRequest;
import com.vti.ufinity.teaching.management.model.Teacher;
import com.vti.ufinity.teaching.management.model.dto.TeacherDTO;
import com.vti.ufinity.teaching.management.model.mapper.TeacherMapper;
import com.vti.ufinity.teaching.management.repository.TeacherRepository;
import com.vti.ufinity.teaching.management.service.validation.TeacherValidationService;
import com.vti.ufinity.teaching.management.utils.DateFormatUtils;
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
public class TeacherService implements CrudService<TeacherDTO> {

    private final TeacherRepository teacherRepository;

    private final TeacherValidationService validationService;

    private final ExceptionMessageAccessor exceptionMessageAccessor;

    @Override
    public List<TeacherDTO> findAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream().map(TeacherMapper.INSTANCE::entityToDto).toList();
    }

    @Override
    public Optional<TeacherDTO> findById(Long id) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        return teacherOptional.map(TeacherMapper.INSTANCE::entityToDto);
    }

    @Transactional
    public TeacherDTO save(TeacherRegisterRequest body) {

        validationService.validateInsert(body);

        final Teacher newTeacher = new Teacher();
        newTeacher.setEmail(body.getEmail());
        newTeacher.setFirstName(body.getFirstName());
        newTeacher.setLastName(body.getLastName());
        newTeacher.setDateOfBirth(DateFormatUtils.parse(body.getDateOfBirth()));

        return TeacherMapper.INSTANCE.entityToDto(teacherRepository.save(newTeacher));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        if (teacherOptional.isEmpty()) {
            return;
        }

        final Teacher teacher = teacherOptional.get();
        teacher.getSubjects().clear();

        teacherRepository.deleteById(id);
    }

    @Transactional
    public TeacherDTO update(Long id, TeacherRegisterRequest body) {

        validationService.validateUpdate(body, id);

        final Teacher teacher = teacherRepository.getReferenceById(id);
        teacher.setEmail(body.getEmail());
        teacher.setFirstName(body.getFirstName());
        teacher.setLastName(body.getLastName());
        teacher.setDateOfBirth(DateFormatUtils.parse(body.getDateOfBirth()));

        return TeacherMapper.INSTANCE.entityToDto(teacherRepository.save(teacher));
    }
}
