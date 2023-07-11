package com.mtm.scm.dto.request;

import com.mtm.scm.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateStudentRequest {

    @NotEmpty(message="name should not be null or empty")
    private String name;

    @NotNull(message="age should not be null")
    private Integer age;

    @NotNull(message="status should not be null")
    private Status status;

}
