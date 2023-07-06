package in.manytoone.accountmanagementsystem.dto.response;

import in.manytoone.accountmanagementsystem.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountResponse {

    private Integer id;
    private String accountNumber;
    private String fullName;
    private BigDecimal balance;

    private Status status;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private String branchCode;

    private String branchName;




}
