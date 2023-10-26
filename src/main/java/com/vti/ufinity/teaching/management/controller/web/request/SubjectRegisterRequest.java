package com.vti.ufinity.teaching.management.controller.web.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectRegisterRequest {

    @NotEmpty(message = "{subject_name_not_empty}")
    private String name;

}