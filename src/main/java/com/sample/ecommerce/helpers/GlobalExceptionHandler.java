package com.sample.ecommerce.helpers;

import com.sample.ecommerce.exceptions.EcommerceException;
import com.sample.ecommerce.pojos.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public Response handleBadCredentialsException(BadCredentialsException e) {
        return Response.builder()
                .status(Response.Status.FAILURE)
                .message("Invalid Credentials")
                .build();
    }

    @ExceptionHandler(EcommerceException.class)
    public Response handleEcommerceException(EcommerceException e) {
        return Response.builder()
                .status(Response.Status.FAILURE)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleValidationException(MethodArgumentNotValidException e) {
        ObjectError firstError = e.getBindingResult().getAllErrors().get(0);
        return Response.builder()
                .status(Response.Status.FAILURE)
                .message(firstError.getDefaultMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        log.error("Error Occurred", e);
        return Response.builder()
                .status(Response.Status.ERROR)
                .message("Something Went Wrong! Please Try Again Later.")
                .build();
    }
}

