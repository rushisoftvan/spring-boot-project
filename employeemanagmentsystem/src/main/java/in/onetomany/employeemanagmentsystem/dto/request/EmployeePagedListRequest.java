package in.onetomany.employeemanagmentsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeePagedListRequest {
    Integer pageNo=1;
    Integer pageSize=5;
}
