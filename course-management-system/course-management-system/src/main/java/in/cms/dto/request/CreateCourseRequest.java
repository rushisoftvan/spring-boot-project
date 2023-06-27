package in.cms.dto.request;

import in.cms.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class CreateCourseRequest {

    private String courseTitle;

    private BigDecimal cousrePrice;


}
