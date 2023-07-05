package in.onetomany.employeemanagmentsystem.dto.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateAddressRequest {

    @NotEmpty(message = "city should not be null or empty")
    private String city;

    @NotNull(message = "house No should not be null")
    private Integer houseNo;
}
