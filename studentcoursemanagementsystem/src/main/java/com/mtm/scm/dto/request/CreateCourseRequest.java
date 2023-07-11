package com.mtm.scm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor

public class CreateCourseRequest {

    @NotEmpty(message = "name should not be null")
    private String name;

    @NotNull(message = "price should not be null")
    private BigDecimal price;

}
