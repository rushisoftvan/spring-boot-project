package com.mtm.scm.exception;

import com.mtm.scm.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ApiResponse<Object> handleCustomException(CustomException ex) {
        String message = ex.getMessage();
        return ApiResponse.builder().errors(Arrays.asList(message)).statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();

    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ApiResponse<Object> handleRecordNotFoundException(RecordNotFoundException ex) {
        String message = ex.getMessage();
        return ApiResponse.builder().errors(Arrays.asList(message)).statusCode(HttpStatus.NOT_FOUND.value()).build();
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<ObjectError> allErrors = methodArgumentNotValidException.getAllErrors();
        List<String> finalErrors = new ArrayList();
        for (ObjectError error : allErrors) {
            finalErrors.add(error.getDefaultMessage());
        }
        return ApiResponse.builder().errors(finalErrors).statusCode(HttpStatus.BAD_REQUEST.value()).build();

    }
    @ExceptionHandler(value= ConstraintViolationException.class)
    public ApiResponse<Object> handleConstraintViolationException(ConstraintViolationException ex){
        String message = ex.getMessage();
        return ApiResponse.builder().errors(Arrays.asList(message)).statusCode(HttpStatus.BAD_REQUEST.value()).build();

    }


}
