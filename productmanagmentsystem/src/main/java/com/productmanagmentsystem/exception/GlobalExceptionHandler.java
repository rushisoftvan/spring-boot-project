package com.productmanagmentsystem.exception;

import com.productmanagmentsystem.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public static final String MESSAGE_OCCURRED = "message occurred";

    @ExceptionHandler(ProductNotFoundException.class)
    public ApiResponse handleProductNotFoundException(final ProductNotFoundException productNotFoundException, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String message = productNotFoundException.getMessage();
        log.error(MESSAGE_OCCURRED, productNotFoundException);
        return new ApiResponse(Arrays.asList(message), HttpStatus.NOT_FOUND.value(), url);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest request) {
        String url=request.getRequestURL().toString();
        List<String> finalErrors = new ArrayList<>();
        List<ObjectError> errors = methodArgumentNotValidException.getAllErrors();
        for(ObjectError objectError : errors){
            String message = objectError.getDefaultMessage();
            finalErrors.add(message);
        }
        log.error(MESSAGE_OCCURRED,methodArgumentNotValidException);
        return new ApiResponse(finalErrors,HttpStatus.BAD_REQUEST.value(),url);
    }

}
