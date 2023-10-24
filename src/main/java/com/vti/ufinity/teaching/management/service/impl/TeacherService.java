package com.vti.ufinity.teaching.management.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vti.ufinity.teaching.management.model.Teacher;
import com.vti.ufinity.teaching.management.model.dto.TeacherDTO;
import com.vti.ufinity.teaching.management.model.mapper.TeacherMapper;
import com.vti.ufinity.teaching.management.repository.TeacherRepository;
import com.vti.ufinity.teaching.management.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService implements CrudService<TeacherDTO> {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @Override
    public List<TeacherDTO> findAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream().map(TeacherMapper.INSTANCE::teacherToDto).collect(
            Collectors.toList());
    }

    @Override
    public Optional<TeacherDTO> findById(Long id) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        return teacherOptional.map(TeacherMapper.INSTANCE::teacherToDto);
    }

    @Override
    public TeacherDTO save(TeacherDTO dto) {
        Teacher newTeacher = TeacherMapper.INSTANCE.dtoToTeacher(dto);
        return TeacherMapper.INSTANCE.teacherToDto(teacherRepository.save(newTeacher));
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public TeacherDTO update(Long id, TeacherDTO dto) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow();
        TeacherMapper.INSTANCE.dtoToTeacherPatch(teacher, dto);

        return TeacherMapper.INSTANCE.teacherToDto(teacherRepository.save(teacher));
    }
}
