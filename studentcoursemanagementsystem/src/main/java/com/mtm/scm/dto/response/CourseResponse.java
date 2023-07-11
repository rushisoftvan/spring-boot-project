package com.mtm.scm.dto.response;

import com.mtm.scm.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CourseResponse {

    private Integer id;
    private String name;
    private BigDecimal price;
    private Status status;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;


}
