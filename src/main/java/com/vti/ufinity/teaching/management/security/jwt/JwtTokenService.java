package com.vti.ufinity.teaching.management.security.jwt;

import com.vti.ufinity.teaching.management.model.User;
import com.vti.ufinity.teaching.management.security.dto.AuthenticatedUserDto;
import com.vti.ufinity.teaching.management.security.dto.LoginRequest;
import com.vti.ufinity.teaching.management.security.dto.LoginResponse;
import com.vti.ufinity.teaching.management.security.mapper.UserMapper;
import com.vti.ufinity.teaching.management.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {

	private final UserService userService;

	private final JwtTokenManager jwtTokenManager;

	private final AuthenticationManager authenticationManager;

	public LoginResponse getLoginResponse(LoginRequest loginRequest) {

		final String username = loginRequest.getUsername();
		final String password = loginRequest.getPassword();

		final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		final AuthenticatedUserDto authenticatedUserDto = userService.findAuthenticatedUserByUsername(username);

		final User user = UserMapper.INSTANCE.convertToUser(authenticatedUserDto);
		final String token = jwtTokenManager.generateToken(user);

		log.info("{} has successfully logged in!", user.getUsername());

		return new LoginResponse(token);
	}

}
