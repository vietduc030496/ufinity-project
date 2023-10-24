package com.vti.ufinity.teaching.management.security.service;

import com.vti.ufinity.teaching.management.model.User;
import com.vti.ufinity.teaching.management.security.dto.AuthenticatedUserDto;
import com.vti.ufinity.teaching.management.security.dto.RegistrationRequest;
import com.vti.ufinity.teaching.management.security.dto.RegistrationResponse;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
public interface UserService {

	User findByUsername(String username);

	RegistrationResponse registration(RegistrationRequest registrationRequest);

	AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

}
