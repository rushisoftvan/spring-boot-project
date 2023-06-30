package in.onetoone.bookmanagementsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BookPagedListRequest {

    @NotNull(message = "pageNumber should not be null")
    private Integer pageNumber = 1;

    @NotNull(message="pageSize should not be null")
    private Integer pageSize=5;
}
