package in.manytomany.studentcoursemanagementsystem.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@Builder
public class ApiResponse<T> {

    private T Data;

    private Integer statusCode;

    private List<String> errors;
}
