package in.onetoone.bookmanagementsystem.Exception;

import in.onetoone.bookmanagementsystem.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecordNotFountException.class)
    public ApiResponse handleRecordNotFoundException (RecordNotFountException recordNotFountException){
      List<String> finalErrors = new ArrayList();
      finalErrors.add(recordNotFountException.getMessage());
       return new ApiResponse(finalErrors, HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        List<ObjectError> allErrors = methodArgumentNotValidException.getAllErrors();
        List<String> finalErrors  = new ArrayList();
        for(ObjectError error : allErrors ){
            finalErrors.add(error.getDefaultMessage());
        }
        return new ApiResponse(finalErrors,HttpStatus.BAD_REQUEST.value());
    }

}
