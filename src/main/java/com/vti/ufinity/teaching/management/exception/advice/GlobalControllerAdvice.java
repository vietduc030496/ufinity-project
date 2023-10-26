package com.vti.ufinity.teaching.management.exception.advice;

import static com.vti.ufinity.teaching.management.utils.constants.MessageCodeConstants.COMMON_ERROR;
import static com.vti.ufinity.teaching.management.utils.constants.MessageCodeConstants.SERVER_SIDE_ERROR;

import java.time.LocalDateTime;
import java.util.List;

import com.vti.ufinity.teaching.management.exception.BadRequestException;
import com.vti.ufinity.teaching.management.exception.ServerSideException;
import com.vti.ufinity.teaching.management.exception.response.ApiExceptionResponse;
import com.vti.ufinity.teaching.management.exception.response.ValidationErrorResponse;
import com.vti.ufinity.teaching.management.utils.message.ExceptionMessageAccessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class GlobalControllerAdvice {

    private final ExceptionMessageAccessor exceptionMessageAccessor;

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ApiExceptionResponse> handleResourceNotFoundException(BadRequestException exception) {

        final ApiExceptionResponse response = ApiExceptionResponse.builder().status(HttpStatus.BAD_REQUEST)
                                                                  .time(LocalDateTime.now())
                                                                  .message(exception.getMessage()).build();

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException exception) {

        final List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        final List<String> errorList = fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                                                  .toList();

        final ValidationErrorResponse validationErrorResponse = ValidationErrorResponse.builder()
                                                                                       .status(HttpStatus.BAD_REQUEST)
                                                                                       .time(LocalDateTime.now())
                                                                                       .messages(errorList).build();

        log.warn("Validation errors : {} , Parameters : {}", errorList, exception.getBindingResult().getTarget());

        return ResponseEntity.status(validationErrorResponse.getStatus()).body(validationErrorResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ApiExceptionResponse> handleRegistrationException(BadCredentialsException exception) {

        final ApiExceptionResponse response = ApiExceptionResponse.builder().status(HttpStatus.UNAUTHORIZED)
                                                                  .time(LocalDateTime.now())
                                                                  .message(exception.getMessage()).build();

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(ServerSideException.class)
    public final ResponseEntity<ApiExceptionResponse> handleServerSideException(ServerSideException exception) {

        final ApiExceptionResponse response = ApiExceptionResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
                                                                  .time(LocalDateTime.now())
                                                                  .message(exceptionMessageAccessor.getMessage(
                                                                      SERVER_SIDE_ERROR)).build();

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiExceptionResponse> handleCommonException(Exception exception) {

        exception.printStackTrace();
        final ApiExceptionResponse response = ApiExceptionResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
                                                                  .time(LocalDateTime.now())
                                                                  .message(exceptionMessageAccessor.getMessage(
                                                                      COMMON_ERROR)).build();

        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
