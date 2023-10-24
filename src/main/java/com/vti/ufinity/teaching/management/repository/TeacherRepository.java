package com.vti.ufinity.teaching.management.repository;

import com.vti.ufinity.teaching.management.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
