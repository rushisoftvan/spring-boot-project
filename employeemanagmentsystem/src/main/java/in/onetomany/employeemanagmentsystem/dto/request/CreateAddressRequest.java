package in.onetomany.employeemanagmentsystem.dto.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CreateAddressRequest {

    @NotEmpty(message= "EmoloyeeName should not be null or empty")
    private String city;

    @NotNull(message ="House no should not be null")
    private Integer houseNo;
}
