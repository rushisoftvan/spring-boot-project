package in.onetomany.employeemanagmentsystem.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class CreateEmployeeRequest {

    @NotEmpty(message= "EmoloyeeName should not be null or empty")
    private String employeeName;

    @NotNull(message="EmoloyeeName should not be null")
    private Integer employeeAge;

    private List<CreateAddressRequest> addresses = new ArrayList<>();
}
