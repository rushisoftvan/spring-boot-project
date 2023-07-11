package com.mtm.scm.mapper;

import com.mtm.scm.entity.CourseEntity;
import com.mtm.scm.entity.StudentEntity;
import com.mtm.scm.dto.response.StudentCourseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.time.LocalDateTime;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentCourseMapper {

          @Mapping(source = "studentEntity.id",target = "studentId")
          @Mapping(source = "courseEntity.id",target="courseId")
          @Mapping(source = "studentEntity.name",target="studentName")
          @Mapping(source = "studentEntity.age",target="studentAge")
          @Mapping(source = "courseEntity.name",target="courseName")
          @Mapping(source = "courseEntity.price",target="coursePrice")
          @Mapping(source = "date",target="enrolDate")
        StudentCourseResponse studentAndCoursetoStudentCourseResponse(StudentEntity studentEntity, CourseEntity courseEntity, LocalDateTime date);


}
