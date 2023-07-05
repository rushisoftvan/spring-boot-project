package in.onetomany.employeemanagmentsystem.dto.response;

import in.onetomany.employeemanagmentsystem.StatusEnum.StatusEnum;
import in.onetomany.employeemanagmentsystem.dto.request.CreateAddressRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class EmployeeResponse {
    private Integer employeeId;

    private String employeeName;

    private Integer employeeAge;

    private StatusEnum employeeStatus;

    private List<AddressResponse> addresses = new ArrayList<>();
}
