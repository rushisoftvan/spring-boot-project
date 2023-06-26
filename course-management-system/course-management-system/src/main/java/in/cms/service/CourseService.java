package in.cms.service;


import in.cms.dto.request.CreateCourseRequest;
import in.cms.entity.CourseEntity;
import in.cms.mapper.CourseMapper;
import in.cms.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

private final CourseRepository courseRepository;
private final CourseMapper courseMapper;
public CourseEntity saveCourse(CreateCourseRequest createCourseRequest){
    CourseEntity course = this.courseMapper.toEntity(createCourseRequest);
    return this.courseRepository.save(course);
}


}
