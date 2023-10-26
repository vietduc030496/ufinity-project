package com.vti.ufinity.teaching.management.repository;

import com.vti.ufinity.teaching.management.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
public interface ClassRepository extends JpaRepository<Class, Long> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}
