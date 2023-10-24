package com.vti.ufinity.teaching.management.configuaration.security;

import com.vti.ufinity.teaching.management.security.jwt.JwtAuthenticationEntryPoint;
import com.vti.ufinity.teaching.management.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    private static final String[] AUTH_WHITELIST = {
        // -- Swagger UI v2
        "/",
        "/hello",
        "/register",
        "/login",
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/swagger-ui.html",
        "/actuator/**"
    };

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration)
        throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //@formatter:off

//        return http.csrf(AbstractHttpConfigurer::disable)
//                   .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                   .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
//                   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                   .authorizeHttpRequests(requests -> requests
//                       .requestMatchers(AUTH_WHITELIST).permitAll()
//                       .anyRequest().authenticated()).build();

        http
            .csrf(AbstractHttpConfigurer::disable)
            .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(requests -> requests
                                   .requestMatchers(AUTH_WHITELIST).permitAll()
                                   .anyRequest().authenticated()
                              );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();


        //@formatter:on
    }

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf(csrf -> csrf.disable())
//			.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
//			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//			.authorizeHttpRequests(auth -> auth.requestMatchers(AUTH_WHITELIST).permitAll()
//											   .requestMatchers("/api/test/**").permitAll()
//											   .anyRequest().authenticated());
//
//		// http....;
//
//		return http.build();
//	}


}
