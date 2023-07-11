package com.mtm.scm.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CoursePageRequest {

    @NotNull(message="page no should not be null")
    private Integer pageNo=1;

    @NotNull(message = "page size should not be null")
    private Integer pageSize=5;
}
