package com.vti.ufinity.teaching.management.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vti.ufinity.teaching.management.model.StudentType;
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
public class StudentDTO extends BaseDTO {

    private String email;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private StudentType type;
}
