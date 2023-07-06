package in.manytoone.accountmanagementsystem.Exception;

import in.manytoone.accountmanagementsystem.dto.response.ApiResponse;
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

    @ExceptionHandler(CustomException.class)
    public ApiResponse<Object> handleCustomException(CustomException ex){
        String message = ex.getMessage();
        ApiResponse.ApiResponseBuilder builder = ApiResponse.builder();
        return ApiResponse.builder().errors(Arrays.asList(message)).statusCode(HttpStatus.BAD_REQUEST.value()).build();
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ApiResponse<Object> handleCustomException(RecordNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse.ApiResponseBuilder builder = ApiResponse.builder();
        return ApiResponse.builder().errors(Arrays.asList(message)).statusCode(HttpStatus.BAD_REQUEST.value()).build();
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<ObjectError> allErrors = methodArgumentNotValidException.getAllErrors();
        List<String> finalErrors = new ArrayList();
        for (ObjectError error : allErrors) {
            finalErrors.add(error.getDefaultMessage());
        }
        return ApiResponse.builder().data(finalErrors).statusCode(HttpStatus.BAD_REQUEST.value()).build();
    }



}
