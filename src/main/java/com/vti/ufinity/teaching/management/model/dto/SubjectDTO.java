package com.vti.ufinity.teaching.management.model.dto;

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
public class SubjectDTO extends BaseDTO {

    public String name;
}
