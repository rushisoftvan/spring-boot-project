package in.cms.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CourseResponse {

    private Integer cousrseId;

    private String courseTitle;

    private BigDecimal cousrePrice;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

}
