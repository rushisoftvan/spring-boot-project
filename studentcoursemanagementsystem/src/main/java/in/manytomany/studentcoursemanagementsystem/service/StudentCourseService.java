package in.manytomany.studentcoursemanagementsystem.service;

import in.manytomany.studentcoursemanagementsystem.dto.StudentCourseDto;
import in.manytomany.studentcoursemanagementsystem.dto.request.BuyCourseRequest;
import in.manytomany.studentcoursemanagementsystem.dto.response.StudentCourseResponse;
import in.manytomany.studentcoursemanagementsystem.entity.CourseEntity;
import in.manytomany.studentcoursemanagementsystem.entity.StudentCourseEntity;
import in.manytomany.studentcoursemanagementsystem.entity.StudentEntity;
import in.manytomany.studentcoursemanagementsystem.exception.CustomException;
import in.manytomany.studentcoursemanagementsystem.mapper.StudentCourseMapper;
import in.manytomany.studentcoursemanagementsystem.repository.StudentCourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

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
        checkBuyRequest(buyCourseRequest);

        StudentEntity studentEntity = this.studentService.fetchStudentEntityById(buyCourseRequest.getStudentId());
        CourseEntity courseEntityById = this.courseService.getCourseEntityById(buyCourseRequest.getCourseId());
        StudentCourseEntity studentCourseEntity = new StudentCourseEntity();
        studentCourseEntity.setCourse(courseEntityById);
        studentCourseEntity.setStudent(studentEntity);
        StudentCourseEntity savedEntity = this.studentCourseRepository.save(studentCourseEntity);
      return  this.studentCourseMapper.studentAndCoursetoStudentCourseResponse(savedEntity.getStudent(),savedEntity.getCourse(),savedEntity.getEnrolledDateTime());
    }

    private static void checkBuyRequest(BuyCourseRequest buyCourseRequest) {
        if(Objects.isNull(buyCourseRequest)){
            throw new CustomException("Buy Course request should not be null");
        }
    }

    public List<StudentCourseDto> fetchStudentstoEnrollInCourse(Integer id){
        this.studentCourseRepository.findStudentstoEnrollInCourse(id);
        return null;
    }

}
