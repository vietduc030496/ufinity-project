package com.vti.ufinity.teaching.management.service;

import java.util.List;
import java.util.Optional;

import com.vti.ufinity.teaching.management.model.dto.BaseDTO;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
public interface CrudService <T extends BaseDTO>{

    List<T> findAll();

    Optional<T> findById(Long id);

    void delete(Long id);

}
