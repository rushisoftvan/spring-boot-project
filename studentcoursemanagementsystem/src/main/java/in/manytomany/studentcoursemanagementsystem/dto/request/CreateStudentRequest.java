package in.manytomany.studentcoursemanagementsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateStudentRequest {

    @NotEmpty(message = "name should not be null or empty")
    private String name;

    @NotNull(message = "age should not be null")
    private Integer age;
}
