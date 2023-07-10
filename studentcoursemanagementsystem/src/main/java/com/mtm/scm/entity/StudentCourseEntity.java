package in.manytomany.studentcoursemanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID")
    private StudentEntity student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COURSE_ID")
    private CourseEntity course;

    @Column(name = "ENROLLED_DATE_TIME")
    private LocalDateTime enrolledDateTime;

    @PrePersist
    public void prePersist() {
        this.enrolledDateTime = LocalDateTime.now();
    }

}


