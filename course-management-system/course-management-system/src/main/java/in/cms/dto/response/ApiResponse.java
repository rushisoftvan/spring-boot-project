package in.cms.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {

    private T data;
    private Integer statusCode;

    private List<String> errors;

    public ApiResponse(T data, Integer statusCode){
        this.data=data;
        this.statusCode=statusCode;
    }

    public ApiResponse(List<String> errors,Integer statusCode){
        this.statusCode=statusCode;
        this.errors=errors;
    }


}
