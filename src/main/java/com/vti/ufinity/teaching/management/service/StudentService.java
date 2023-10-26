package com.vti.ufinity.teaching.management.service;

import static com.vti.ufinity.teaching.management.utils.constants.MessageCodeConstants.RESOURCE_NOT_FOUND;
import static com.vti.ufinity.teaching.management.utils.constants.MessageCodeConstants.STUDENT_ENROLLED;
import static com.vti.ufinity.teaching.management.utils.constants.MessageCodeConstants.UPDATE_SUCCESSFUL;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.vti.ufinity.teaching.management.controller.web.request.StudentRegisterRequest;
import com.vti.ufinity.teaching.management.exception.ResourceNotFoundException;
import com.vti.ufinity.teaching.management.model.Class;
import com.vti.ufinity.teaching.management.model.Student;
import com.vti.ufinity.teaching.management.model.StudentType;
import com.vti.ufinity.teaching.management.model.dto.StudentDTO;
import com.vti.ufinity.teaching.management.model.mapper.StudentMapper;
import com.vti.ufinity.teaching.management.repository.ClassRepository;
import com.vti.ufinity.teaching.management.repository.StudentRepository;
import com.vti.ufinity.teaching.management.service.validation.StudentValidationService;
import com.vti.ufinity.teaching.management.utils.DateFormatUtils;
import com.vti.ufinity.teaching.management.utils.message.ExceptionMessageAccessor;
import com.vti.ufinity.teaching.management.utils.message.GeneralMessageAccessor;
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
public class StudentService implements CrudService<StudentDTO> {

    private final StudentRepository studentRepository;

    private final ClassRepository classRepository;

    private final StudentValidationService studentValidationService;

    private final ExceptionMessageAccessor exceptionMessageAccessor;

    private final GeneralMessageAccessor generalMessageAccessor;

    @Override
    public List<StudentDTO> findAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(StudentMapper.INSTANCE::entityToDto).toList();
    }

    @Override
    public Optional<StudentDTO> findById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional.map(StudentMapper.INSTANCE::entityToDto);
    }

    @Transactional
    public StudentDTO save(StudentRegisterRequest body) {

        studentValidationService.validateInsert(body);

        final Student newStudent = new Student();
        newStudent.setEmail(body.getEmail());
        newStudent.setFirstName(body.getFirstName());
        newStudent.setLastName(body.getLastName());
        newStudent.setType(StudentType.INTERNAL);
        newStudent.setDateOfBirth(DateFormatUtils.parse(body.getDateOfBirth()));

        return StudentMapper.INSTANCE.entityToDto(studentRepository.save(newStudent));
    }

    @Transactional
    public void delete(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            return;
        }

        final Student student = studentOptional.get();
        final Set<Class> aClass = student.getAClass();

        aClass.forEach(c -> c.getStudents().remove(student));
        aClass.clear();

        studentRepository.deleteById(id);
    }

    @Transactional
    public StudentDTO update(Long id, StudentRegisterRequest body) {

        studentValidationService.validateUpdate(body, id);

        Student student = studentRepository.getReferenceById(id);
        student.setEmail(body.getEmail());
        student.setFirstName(body.getFirstName());
        student.setLastName(body.getLastName());
        student.setType(StudentType.INTERNAL);
        student.setDateOfBirth(DateFormatUtils.parse(body.getDateOfBirth()));

        return StudentMapper.INSTANCE.entityToDto(studentRepository.save(student));
    }

    @Transactional
    public String enrollClass(Long id, Long classId) {

        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            log.warn("Not found student with id {}!", id);

            throw new ResourceNotFoundException(exceptionMessageAccessor.getMessage(RESOURCE_NOT_FOUND, id));
        }

        Optional<Class> classOptional = classRepository.findById(classId);
        if (classOptional.isEmpty()) {

            log.warn("Not found class with id {}!", classId);
            throw new ResourceNotFoundException(exceptionMessageAccessor.getMessage(RESOURCE_NOT_FOUND, id));
        }

        final Student student = studentOptional.get();
        final Class aClass = classOptional.get();
        final Set<Class> aClassSet = student.getAClass();
        final Set<Student> students = aClass.getStudents();

        if (aClassSet.contains(aClass) || students.contains(student)) {

            return generalMessageAccessor.getMessage(STUDENT_ENROLLED);
        }

        student.getAClass().add(aClass);
        aClass.getStudents().add(student);

        return generalMessageAccessor.getMessage(UPDATE_SUCCESSFUL);
    }

    @Transactional
    public String deregisterClass(Long id, Long classId) {

        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            log.warn("Not found student with id {}!", id);

            throw new ResourceNotFoundException(exceptionMessageAccessor.getMessage(RESOURCE_NOT_FOUND, id));
        }

        Optional<Class> classOptional = classRepository.findById(classId);
        if (classOptional.isEmpty()) {

            log.warn("Not found class with id {}!", classId);
            throw new ResourceNotFoundException(exceptionMessageAccessor.getMessage(RESOURCE_NOT_FOUND, id));
        }

        final Student student = studentOptional.get();
        final Class aClass = classOptional.get();

        student.getAClass().remove(aClass);
        aClass.getStudents().remove(student);

        return generalMessageAccessor.getMessage(UPDATE_SUCCESSFUL);
    }
}
