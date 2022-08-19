package com.techelevator.exception;

import com.techelevator.model.UserAlreadyExistsException;
import okhttp3.Response;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.postgresql.util.PSQLException;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        ResponseEntity<String> response = new ResponseEntity<>("User Already Exists", HttpStatus.BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<String> handlePSQLException(PSQLException exception) {
        String message = "UNKNOWN ERROR";

        if (exception.getServerErrorMessage().getRoutine().equalsIgnoreCase("_bt_check_unique")) {
            String constraint = exception.getServerErrorMessage().getConstraint();
            if (constraint.equalsIgnoreCase("users_email_address_key")) {
                message = "Email address is already in use";
            } else if (constraint.equalsIgnoreCase("genre_genre_name_key")) {
                message = "Genre names must be unique";
            }
        }

        ResponseEntity<String> response = new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> handleDuplicateKeyException(DuplicateKeyException exception) {
        if (exception.getCause().getClass().isAssignableFrom(PSQLException.class)) {
            return handlePSQLException((PSQLException) exception.getCause());
        }
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationError(MethodArgumentNotValidException exception) {
        String message = Optional.ofNullable(exception).map(MethodArgumentNotValidException::getBindingResult).map(BindingResult::getAllErrors).orElse(Collections.emptyList())
                .stream().map(error->
                    String.join(".\n", error.getDefaultMessage().split(".,"))
                ).collect(Collectors.joining());

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        ResponseEntity<String> response = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException exception) {
        ResponseEntity<String> response = new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        return response;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDDeniedException(AccessDeniedException exception) {
        return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);
    }
}
