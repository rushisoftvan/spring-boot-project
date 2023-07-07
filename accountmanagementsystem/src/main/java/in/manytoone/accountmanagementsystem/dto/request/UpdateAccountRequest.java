package in.manytoone.accountmanagementsystem.dto.request;

import in.manytoone.accountmanagementsystem.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class UpdateAccountRequest {
    private String accountNumber;
    private String fullName;
    private BigDecimal balance;
    private Status status;

    private Integer branchId;
}
