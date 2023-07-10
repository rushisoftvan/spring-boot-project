package in.manytomany.studentcoursemanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter

public class StudentCourseDto {

    private Integer studentId;

    private String studentName;

    private String studentAge;
    private Integer courseId;
    private String courseName;
    private String coursePrice;
    private LocalDateTime enrolDate;
}



