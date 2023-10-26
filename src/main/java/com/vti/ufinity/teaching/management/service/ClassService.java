package com.vti.ufinity.teaching.management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.vti.ufinity.teaching.management.controller.web.request.ClassRegisterRequest;
import com.vti.ufinity.teaching.management.model.Class;
import com.vti.ufinity.teaching.management.model.Student;
import com.vti.ufinity.teaching.management.model.Subject;
import com.vti.ufinity.teaching.management.model.Teacher;
import com.vti.ufinity.teaching.management.model.dto.ClassDTO;
import com.vti.ufinity.teaching.management.model.dto.StudentDTO;
import com.vti.ufinity.teaching.management.model.mapper.ClassMapper;
import com.vti.ufinity.teaching.management.model.mapper.StudentMapper;
import com.vti.ufinity.teaching.management.repository.ClassRepository;
import com.vti.ufinity.teaching.management.repository.SubjectRepository;
import com.vti.ufinity.teaching.management.repository.TeacherRepository;
import com.vti.ufinity.teaching.management.service.validation.ClassValidationService;
import com.vti.ufinity.teaching.management.utils.message.ExceptionMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ClassService implements CrudService<ClassDTO>{

    private final ClassRepository classRepository;

    private final SubjectRepository subjectRepository;

    private final TeacherRepository teacherRepository;

    private final ClassValidationService validationService;

    private ExceptionMessageAccessor exceptionMessageAccessor;

    @Override
    public List<ClassDTO> findAll() {

        List<ClassDTO> result = new ArrayList<>();

        List<Class> allClass = classRepository.findAll();
        for (Class clazz : allClass) {

            ClassDTO classDTO = ClassMapper.INSTANCE.entityToDto(clazz);

            final Set<Student> students = clazz.getStudents();
            if (!CollectionUtils.isEmpty(students)) {

                List<StudentDTO> studentDTOS = students.stream().map(StudentMapper.INSTANCE::entityToDto).toList();
                classDTO.setStudent(studentDTOS);
            }

            result.add(classDTO);
        }

        return result;
    }

    @Override
    public Optional<ClassDTO> findById(Long id) {

        Optional<Class> optionalClass = classRepository.findById(id);
        return optionalClass.map(ClassMapper.INSTANCE::entityToDto);
    }

    @Transactional
    public ClassDTO save(ClassRegisterRequest body) {

        validationService.validateInsert(body);

        Subject subject = subjectRepository.getReferenceById(body.getSubjectId());
        Teacher teacher = teacherRepository.getReferenceById(body.getTeacherId());

        Class newClazz = new Class();
        newClazz.setName(body.getName());
        newClazz.setSubject(subject);
        newClazz.setTeacher(teacher);

        return ClassMapper.INSTANCE.entityToDto(classRepository.save(newClazz));
    }

    @Override
    public void delete(Long id) {
        Optional<Class> classOptional = classRepository.findById(id);
        if (classOptional.isEmpty()) {
            return;
        }

        final Class aClass = classOptional.get();
        final Set<Student> students = aClass.getStudents();

        aClass.setTeacher(null);
        aClass.setSubject(null);
        students.forEach(s -> s.getAClass().remove(aClass));
        aClass.getStudents().clear();

        classRepository.deleteById(id);
    }

    @Transactional
    public ClassDTO update(Long id, ClassRegisterRequest body) {

        validationService.validateUpdate(body, id);

        Subject subject = subjectRepository.getReferenceById(body.getSubjectId());
        Teacher teacher = teacherRepository.getReferenceById(body.getTeacherId());

        Class clazz = classRepository.getReferenceById(id);
        clazz.setName(body.getName());
        clazz.setSubject(subject);
        clazz.setTeacher(teacher);

        return ClassMapper.INSTANCE.entityToDto(classRepository.save(clazz));
    }
}
