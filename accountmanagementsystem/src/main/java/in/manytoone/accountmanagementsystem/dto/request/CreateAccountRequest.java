package in.manytoone.accountmanagementsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class CreateAccountRequest {

    @NotNull(message = "Branch id should not be null")
    private Integer branchId;

    @NotEmpty(message="Account Number should not be null or empty")
    private String accountNumber;

    @NotEmpty(message="Full Name  should not be null or empty")
    private String fullName;

    @NotNull(message = "Balance should not be null")
    private BigDecimal balance;


}
