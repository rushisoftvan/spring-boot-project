package in.onetomany.employeemanagmentsystem.dto.response;

import in.onetomany.employeemanagmentsystem.StatusEnum.StatusEnum;
import in.onetomany.employeemanagmentsystem.dto.request.CreateAddressRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EmployeeResponse {
    private Integer employeeId;

    private String employeeName;

    private Integer employeeAge;

    private StatusEnum employeeStatus;

    private List<CreateAddressRequest> addresses = new ArrayList<>();
}
