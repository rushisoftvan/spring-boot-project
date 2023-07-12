package com.mtm.scm.repository;

import com.mtm.scm.dto.StudentCourseDto;
import com.mtm.scm.entity.StudentCourseEntity;
import com.mtm.scm.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;



public interface StudentCourseRepository extends JpaRepository<StudentCourseEntity, Integer> {
   @Query(value = "SELECT" +
           " new com.mtm.scm.dto.StudentCourseDto(s.id,s.name,s.age,c.id,c.name,c.price,sc.enrolledDateTime)" +
           " FROM StudentCourseEntity sc" +
           " JOIN sc.student s" +
           " JOIN sc.course c" +
           " WHERE c.id = :courseId")
    List<StudentCourseDto> findStudentstoEnrollInCourse(@Param("courseId") Integer courseId);


   @Query(value = "select s.id,s.name,s.age from StudentCourseEntity sc" +
           " JOIN sc.course c" +
           " RIGHT JOIN sc.student s" +
           " where c.id=null")
     List<StudentCourseEntity> findStudentNotBuyAnyCourse();

}

