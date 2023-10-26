package com.vti.ufinity.teaching.management.controller.web.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRegisterRequest implements Serializable {

    @Email(message = "{registration_email_is_not_valid}")
    @NotEmpty(message = "{registration_email_not_empty}")
    private String email;

    @JsonProperty("first_name")
    @NotEmpty(message = "{registration_first_name_not_empty}")
    private String firstName;

    @JsonProperty("last_name")
    @NotEmpty(message = "{registration_last_name_not_empty}")
    private String lastName;

    @JsonProperty("date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotEmpty(message = "{registration_date_of_birth_not_empty}")
    private String dateOfBirth;

}
