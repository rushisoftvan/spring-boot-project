package in.manytoone.accountmanagementsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UpdateBranchRequest {
    @NotEmpty(message ="branch code should be not nll or empty")
    private String branchCode;

    @NotEmpty(message="branch name should be not nll or empty")
    private String branchName;
}
