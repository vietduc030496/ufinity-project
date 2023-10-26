package com.vti.ufinity.teaching.management.controller.web.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassRegisterRequest implements Serializable {

    @NotEmpty(message = "{registration_name_not_empty}")
    private String name;

    @JsonProperty("teacher_id")
    @NotNull(message = "{registration_teacher_id_not_empty}")
    private Long teacherId;

    @JsonProperty("subject_id")
    @NotNull(message = "{registration_subject_id_not_empty}")
    private Long subjectId;

}
