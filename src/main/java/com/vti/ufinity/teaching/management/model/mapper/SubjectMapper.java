package com.vti.ufinity.teaching.management.model.mapper;

import com.vti.ufinity.teaching.management.model.Subject;
import com.vti.ufinity.teaching.management.model.dto.SubjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubjectMapper extends BaseMapper<Subject, SubjectDTO> {

    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

}
