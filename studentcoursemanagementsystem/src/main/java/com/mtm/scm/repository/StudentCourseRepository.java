package in.manytomany.studentcoursemanagementsystem.repository;

import in.manytomany.studentcoursemanagementsystem.dto.StudentCourseDto;
import in.manytomany.studentcoursemanagementsystem.entity.StudentCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface StudentCourseRepository extends JpaRepository<StudentCourseEntity, Integer> {
    @Query( value = "select " +
            "new in.manytomany.studentcoursemanagementsystem.dto.StudentCourseDto(s.id,s.name,s.age,c.id,c.name,c.price,sc.enrolledDateTime) " +
            "from StudentCourseEntity sc" +
            " join sc.student s " +
            "join sc.course c " +
            "where c.id= :courseId")
    List<StudentCourseDto> findStudentstoEnrollInCourse(@Param("courseId") Integer courseId);


}
