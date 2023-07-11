package com.mtm.scm.dto.request;

import com.mtm.scm.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class UpdateCourseRequest {



    @NotEmpty(message = "name should not be null or empty")
    private String name;

    @NotNull(message="price should not be null")
    private BigDecimal price;

    @NotNull(message="status should not null")
    private Status status;





}
