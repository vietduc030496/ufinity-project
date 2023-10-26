package com.vti.ufinity.teaching.management.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vti.ufinity.teaching.management.utils.DateFormatUtils;
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
public class TeacherDTO extends BaseDTO {

    private String email;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatUtils.DATE_MMDDYYYY_LONG)
    private String dateOfBirth;
}
