package com.mtm.scm.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@RequiredArgsConstructor
public class StudentCourseDto {

    private final Integer studentId;

    private final String studentName;

    private final Integer studentAge;
    private final Integer courseId;
    private final String courseName;
    private final BigDecimal coursePrice;
    private final LocalDateTime enrolDate;

}



