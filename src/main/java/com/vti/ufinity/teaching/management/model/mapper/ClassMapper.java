package com.vti.ufinity.teaching.management.model.mapper;

import com.vti.ufinity.teaching.management.model.Class;
import com.vti.ufinity.teaching.management.model.dto.ClassDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClassMapper extends BaseMapper<Class, ClassDTO> {

    ClassMapper INSTANCE = Mappers.getMapper(ClassMapper.class);

}
