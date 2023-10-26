package com.vti.ufinity.teaching.management.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@SuperBuilder
@Getter
@Setter
public class ClassDTO extends BaseDTO {

    private String name;

    private TeacherDTO teacher;

    private SubjectDTO subject;

    private List<StudentDTO> student;

}
