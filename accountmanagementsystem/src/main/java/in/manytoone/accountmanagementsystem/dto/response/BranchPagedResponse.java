package in.manytoone.accountmanagementsystem.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BranchPagedResponse {

    List<BranchResponse> branchResponseList;
    private long totalRecords;

    private boolean isFirstPage;

    private boolean isLastPage;

    private boolean hasPrevious;

    private boolean hasNext;
}
