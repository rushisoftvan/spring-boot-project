package com.mtm.scm.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BuyCourseRequest {

    @NotNull(message="student id should not be null ")
    private Integer studentId;

    @NotNull(message="course id should not be null")
    private Integer courseId;
}
