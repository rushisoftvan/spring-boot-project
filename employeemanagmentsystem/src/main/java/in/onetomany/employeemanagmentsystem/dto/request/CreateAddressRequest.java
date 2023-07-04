package in.onetomany.employeemanagmentsystem.dto.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateAddressRequest {

    private String city;

    private Integer houseNo;
}
