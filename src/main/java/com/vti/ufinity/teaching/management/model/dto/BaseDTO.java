package com.vti.ufinity.teaching.management.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDTO {

//    @ApiModelProperty(value = "The id of the object")
    private Long id;

//    @ApiModelProperty(value = "The date when the object was created")
    private Date creationDate;
}
