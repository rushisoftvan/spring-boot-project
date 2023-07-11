package com.mtm.scm.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PageStudentListRequest {

    @NotNull(message = "page number should not be null")
    private Integer pageNo;

    @NotNull(message = "pageSize should not be null")
    private Integer pageSize;
}
