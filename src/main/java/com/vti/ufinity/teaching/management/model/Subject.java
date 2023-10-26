package com.vti.ufinity.teaching.management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Table(name = "SUBJECT")
@Entity
@Getter
@Setter
public class Subject extends BaseEntity {

    @Column(unique = true)
    private String name;
}
