package com.mtm.scm.service;

import com.mtm.scm.repository.StudentCourseRepository;
import com.mtm.scm.dto.StudentCourseDto;
import com.mtm.scm.dto.request.BuyCourseRequest;
import com.mtm.scm.dto.response.StudentCourseResponse;
import com.mtm.scm.entity.CourseEntity;
import com.mtm.scm.entity.StudentCourseEntity;
import com.mtm.scm.entity.StudentEntity;
import com.mtm.scm.exception.CustomException;
import com.mtm.scm.mapper.StudentCourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentCourseService {

    private final StudentService  studentService;

    private final CourseService  courseService;

    private final StudentCourseRepository studentCourseRepository;

    private final StudentCourseMapper studentCourseMapper;


    public StudentCourseResponse buyCourseByStudent(BuyCourseRequest buyCourseRequest) {

        log.debug("<<<<<<<<< buyCourseByStudent()");

        checkBuyRequest(buyCourseRequest);

        StudentEntity studentEntity = this.studentService.fetchStudentEntityById(buyCourseRequest.getStudentId());
        CourseEntity courseEntityById = this.courseService.getCourseEntityById(buyCourseRequest.getCourseId());
        StudentCourseEntity studentCourseEntity = new StudentCourseEntity();
        studentCourseEntity.setCourse(courseEntityById);
        studentCourseEntity.setStudent(studentEntity);
        StudentCourseEntity savedEntity = this.studentCourseRepository.save(studentCourseEntity);

        log.debug("buyCourseByStudent() >>>>>>>");
      return  this.studentCourseMapper.studentAndCoursetoStudentCourseResponse(savedEntity.getStudent(),savedEntity.getCourse(),savedEntity.getEnrolledDateTime());
    }

    private static void checkBuyRequest(BuyCourseRequest buyCourseRequest) {
        if(Objects.isNull(buyCourseRequest)){
            throw new CustomException("Buy Course request should not be null");
        }
    }

    public List<StudentCourseDto> fetchStudentstoEnrollInCourse(Integer id){
        return this.studentCourseRepository.findStudentstoEnrollInCourse(id);
    }

    @Transactional
    public void getStudentWhoHasNoCourse(){
        log.debug("start");
        List<StudentCourseEntity> studentNotBuyAnyCourse = this.studentCourseRepository.findStudentNotBuyAnyCourse();
        log.debug("studentcourse :: {}",studentNotBuyAnyCourse);
    }


}
