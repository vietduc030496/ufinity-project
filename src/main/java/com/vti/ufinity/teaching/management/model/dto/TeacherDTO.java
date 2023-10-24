package com.vti.ufinity.teaching.management.model.dto;

import java.util.List;

import com.vti.ufinity.teaching.management.model.Subject;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class TeacherDTO extends BaseDTO {
    private String email;

    private String firstName;

    private String lastName;

    private List<Subject> subjects;
}
