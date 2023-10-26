package com.vti.ufinity.teaching.management.repository;

import com.vti.ufinity.teaching.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	boolean existsByUsername(String username);

}
