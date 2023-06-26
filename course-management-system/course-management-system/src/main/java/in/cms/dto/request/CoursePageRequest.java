package in.cms.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoursePageRequest {

    private Integer pageNumber = 1;

    private Integer PageSize = 5;

    private String searchText; // currently we are having only one filter

    private String sortOrder="desc";

    private String sortBy="updatedOn";
}
