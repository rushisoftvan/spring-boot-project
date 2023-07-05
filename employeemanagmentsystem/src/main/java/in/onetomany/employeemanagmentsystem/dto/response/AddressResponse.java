package in.onetomany.employeemanagmentsystem.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressResponse {


    private Integer addressId;

    private String city;

    private Integer houseNo;
}
