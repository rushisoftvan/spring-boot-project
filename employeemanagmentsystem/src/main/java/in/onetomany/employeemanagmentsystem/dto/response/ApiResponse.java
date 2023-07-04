package in.onetomany.employeemanagmentsystem.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Builder
@Getter
@Setter
public class ApiResponse<T> {
    private T data;
    private Integer statusCode;
    private List<String> errors;

}
