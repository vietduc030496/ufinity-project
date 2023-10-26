package com.vti.ufinity.teaching.management.repository;

import com.vti.ufinity.teaching.management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);
}
