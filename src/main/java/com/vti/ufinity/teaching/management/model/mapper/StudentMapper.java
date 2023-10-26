package com.vti.ufinity.teaching.management.model.mapper;

import com.vti.ufinity.teaching.management.model.Student;
import com.vti.ufinity.teaching.management.model.dto.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper extends BaseMapper<Student, StudentDTO> {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

}
