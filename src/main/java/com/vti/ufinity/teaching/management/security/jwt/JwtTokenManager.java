package com.vti.ufinity.teaching.management.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.vti.ufinity.teaching.management.model.User;
import com.vti.ufinity.teaching.management.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Component
@RequiredArgsConstructor
public class JwtTokenManager {

    private final JwtProperties jwtProperties;

    public String generateToken(User user) {

        final String username = user.getUsername();
        final UserRole userRole = user.getUserRole();

        //@formatter:off
        return JWT.create()
                  .withSubject(username)
                  .withIssuer(jwtProperties.getIssuer())
                  .withClaim("role", userRole.name())
                  .withIssuedAt(new Date())
                  .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMinute() * 60 * 1000))
                  .sign(Algorithm.HMAC256(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8)));
        //@formatter:on
    }

    public String getUsernameFromToken(String token) {

        final DecodedJWT decodedJWT = getDecodedJWT(token);

        return decodedJWT.getSubject();
    }

    public boolean validateToken(String token, String authenticatedUsername) {

        final String usernameFromToken = getUsernameFromToken(token);

        final boolean equalsUsername = usernameFromToken.equals(authenticatedUsername);
        final boolean tokenExpired = isTokenExpired(token);

        return equalsUsername && !tokenExpired;
    }

    private boolean isTokenExpired(String token) {

        final Date expirationDateFromToken = getExpirationDateFromToken(token);
        return expirationDateFromToken.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {

        final DecodedJWT decodedJWT = getDecodedJWT(token);

        return decodedJWT.getExpiresAt();
    }

    private DecodedJWT getDecodedJWT(String token) {

        final JWTVerifier jwtVerifier = JWT.require(
            Algorithm.HMAC256(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8))).build();

        return jwtVerifier.verify(token);
    }

}
