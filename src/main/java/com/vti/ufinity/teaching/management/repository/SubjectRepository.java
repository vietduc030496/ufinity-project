package com.vti.ufinity.teaching.management.repository;

import com.vti.ufinity.teaching.management.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}
