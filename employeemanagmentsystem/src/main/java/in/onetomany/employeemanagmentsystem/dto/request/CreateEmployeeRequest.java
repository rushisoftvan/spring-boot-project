package in.onetomany.employeemanagmentsystem.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class CreateEmployeeRequest {

    private String employeeName;

    private Integer employeeAge;

    private List<CreateAddressRequest> addresses = new ArrayList<>();
}
