package in.manytomany.studentcoursemanagementsystem.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentCourseResponse {

    private Integer studentId;
    private Integer courseId;

    private String studentName;

    private String studentAge;

    private String courseName;

    private String coursePrice;

    private LocalDateTime enrolDate;
}
