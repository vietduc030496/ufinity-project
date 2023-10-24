package com.vti.ufinity.teaching.management.model.mapper;

import com.vti.ufinity.teaching.management.model.Teacher;
import com.vti.ufinity.teaching.management.model.dto.TeacherDTO;
import com.vti.ufinity.teaching.management.security.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    Teacher dtoToTeacher(TeacherDTO dto);

    TeacherDTO teacherToDto(Teacher teacher);

    @Mapping(target = "id", source = "source.id")
    @Mapping(target = "email", source = "dto.email")
    @Mapping(target = "firstName", source = "dto.firstName")
    @Mapping(target = "lastName", source = "dto.lastName")
    @Mapping(target = "subjects", source = "dto.subjects")
    Teacher dtoToTeacherPatch(Teacher source, TeacherDTO dto);
}
