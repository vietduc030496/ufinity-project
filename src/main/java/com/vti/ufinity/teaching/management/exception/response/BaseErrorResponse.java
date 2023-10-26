package com.vti.ufinity.teaching.management.exception.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@SuperBuilder
public abstract class BaseErrorResponse {

    private HttpStatus status;

    private LocalDateTime time;
}
