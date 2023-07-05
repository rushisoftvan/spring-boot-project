package in.onetomany.employeemanagmentsystem.dto.response;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeePageListResponse {
   private List<EmployeeResponse> employeeResponseList;
   private Long totalRecords;

}
