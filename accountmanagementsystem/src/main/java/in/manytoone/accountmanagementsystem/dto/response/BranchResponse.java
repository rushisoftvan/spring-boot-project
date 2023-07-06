package in.manytoone.accountmanagementsystem.dto.response;

import in.manytoone.accountmanagementsystem.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BranchResponse {

    private Integer accountId;
    private String branchCode;

    private String branchName;

    private Status status;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;


}
