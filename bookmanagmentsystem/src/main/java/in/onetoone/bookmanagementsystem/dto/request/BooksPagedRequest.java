package in.onetoone.bookmanagementsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BooksPagedRequest {

    Integer pageNo = 1;
    Integer pageSize=5;
}
