package in.manytoone.accountmanagementsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class BranchPageRequest {

    @Positive(message = "page no should be greater than zero")
    @NotNull(message = "page no should not be null")
    public Integer pageNo=1;

    @Positive(message = "page no should be greater than zero")
    @NotNull(message = "page no should not be null")
    public Integer pageSize=5;

}
