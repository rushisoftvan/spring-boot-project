package in.onetomany.employeemanagmentsystem.Exception;


import in.onetomany.employeemanagmentsystem.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<ObjectError> allErrors = methodArgumentNotValidException.getAllErrors();
        List<String> finalErrors = new ArrayList();
        for (ObjectError error : allErrors) {
            finalErrors.add(error.getDefaultMessage());
        }
        return ApiResponse.builder().data(finalErrors).statusCode(HttpStatus.BAD_REQUEST.value()).build();
    }



    @ExceptionHandler(value = RuntimeException.class)
    public ApiResponse.ApiResponseBuilder<Object> handleRuntimeException(RuntimeException ex){
        String msg ="somthing is wrong";
        return ApiResponse.builder().errors(Arrays.asList(msg)).statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
     
    @ExceptionHandler(value=CustomException.class)
    public ApiResponse<Object> handleCustomExcpption(CustomException ex){
        String message = ex.getMessage();
        return ApiResponse.builder().errors(Arrays.asList(message)).statusCode(HttpStatus.BAD_REQUEST.value()).build();
    }
}
