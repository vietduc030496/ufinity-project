package com.vti.ufinity.teaching.management.model.mapper;

import com.vti.ufinity.teaching.management.model.Teacher;
import com.vti.ufinity.teaching.management.model.dto.TeacherDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherMapper extends BaseMapper<Teacher, TeacherDTO> {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

}
