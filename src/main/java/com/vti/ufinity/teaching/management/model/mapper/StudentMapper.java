package com.vti.ufinity.teaching.management.model.mapper;

import com.vti.ufinity.teaching.management.controller.web.request.StudentRegisterExternalRequest;
import com.vti.ufinity.teaching.management.model.Student;
import com.vti.ufinity.teaching.management.model.dto.StudentDTO;
import com.vti.ufinity.teaching.management.utils.DateFormatUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

    @Mapping(source = "dateOfBirth", target = "dateOfBirth", dateFormat = DateFormatUtils.DATE_FORMAT_DEFAULT)
    Student externalToEntity(StudentRegisterExternalRequest request);

}
