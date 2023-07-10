package in.manytomany.studentcoursemanagementsystem.service;

import in.manytomany.studentcoursemanagementsystem.dto.request.CreateCourseRequest;
import in.manytomany.studentcoursemanagementsystem.dto.response.CourseResponse;
import in.manytomany.studentcoursemanagementsystem.entity.CourseEntity;
import in.manytomany.studentcoursemanagementsystem.exception.CustomException;
import in.manytomany.studentcoursemanagementsystem.exception.RecordNotFoundException;
import in.manytomany.studentcoursemanagementsystem.mapper.CourseMapper;
import in.manytomany.studentcoursemanagementsystem.repository.CourseRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CourseService {


    private final CourseRepository courseRepository;


    private final CourseMapper courseMapper;


    public CourseResponse saveCourse(CreateCourseRequest createCourseRequest) {
        checkCreateCourseRequest(createCourseRequest);
        CourseEntity courseEntity = this.courseMapper.toEntity(createCourseRequest);
        CourseEntity savedEntity = this.courseRepository.save(courseEntity);
        return this.courseMapper.toDto(savedEntity);
    }

    private static void checkCreateCourseRequest(CreateCourseRequest createCourseRequest) {
        if (Objects.isNull(createCourseRequest)) {
            throw new CustomException("createcourse request should not be null");
        }
    }

    public CourseResponse fetchCourseById(Integer id) {
        CourseEntity courseEntityById = getCourseEntityById(id);
        return this.courseMapper.toDto(courseEntityById);
    }

    public CourseEntity getCourseEntityById(Integer id) {
        Optional<CourseEntity> dbCourseData = this.courseRepository.findById(id);
        if (dbCourseData.isPresent()) {
            return dbCourseData.get();
        }
        throw new RecordNotFoundException("Record not found for thid id :" + id);
    }


}
