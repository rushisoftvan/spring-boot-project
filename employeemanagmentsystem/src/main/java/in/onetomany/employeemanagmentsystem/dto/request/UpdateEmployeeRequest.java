package in.onetomany.employeemanagmentsystem.dto.request;

import java.util.ArrayList;
import java.util.List;

public class UpdateEmployeeRequest {


    private String employeeName;

    private Integer employeeAge;

    private List<UpdateEmployeeRequest  > addresses = new ArrayList<>();
}
