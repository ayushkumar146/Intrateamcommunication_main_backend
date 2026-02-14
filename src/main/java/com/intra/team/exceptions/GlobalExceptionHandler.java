package com.intra.team.exceptions;

import com.intra.team.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ Already exists
    @ExceptionHandler(ProjectAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleExists(
            ProjectAlreadyExistsException ex,
            HttpServletRequest req) {

        return build(HttpStatus.CONFLICT, ex.getMessage(), req);
    }

    // ✅ Not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest req) {

        return build(HttpStatus.NOT_FOUND, ex.getMessage(), req);
    }

    // ✅ Bad request
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(
            BadRequestException ex,
            HttpServletRequest req) {

        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), req);
    }

    // ✅ Validation errors (@Valid DTO)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest req) {

        String msg = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return build(HttpStatus.BAD_REQUEST, msg, req);
    }

    // ✅ Security — wrong credentials
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCred(
            BadCredentialsException ex,
            HttpServletRequest req) {

        return build(HttpStatus.UNAUTHORIZED,
                "Invalid username or password", req);
    }

    // ✅ Security — role not allowed
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleDenied(
            AccessDeniedException ex,
            HttpServletRequest req) {

        return build(HttpStatus.FORBIDDEN,
                "Access denied", req);
    }

    // ✅ Fallback — everything else
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(
            Exception ex,
            HttpServletRequest req) {

        ex.printStackTrace();

        return build(HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(), req);
    }

    // ===== helper =====

    private ResponseEntity<ErrorResponse> build(
            HttpStatus status,
            String message,
            HttpServletRequest req) {

        ErrorResponse err = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(req.getRequestURI())
                .build();

        return new ResponseEntity<>(err, status);
    }
}