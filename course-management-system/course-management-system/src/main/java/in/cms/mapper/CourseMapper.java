package in.cms.mapper;

import in.cms.dto.request.CreateCourseRequest;
import in.cms.dto.response.CourseResponse;
import in.cms.entity.CourseEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {


    public CourseEntity toEntity(CreateCourseRequest createCourseRequest) {
        CourseEntity course = new CourseEntity();
        course.setTitle(createCourseRequest.getCourseTitle());
        course.setPrice(createCourseRequest.getCousrePrice());
        return course;
    }

    public CourseResponse toDto(CourseEntity courseEntity) {
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setCousrseId(courseEntity.getId());
        courseResponse.setCourseTitle(courseEntity.getTitle());
        courseResponse.setCousrePrice(courseEntity.getPrice());
        courseResponse.setCreatedOn(courseEntity.getCreatedOn());
        courseResponse.setUpdatedOn(courseEntity.getUpdatedOn());
        return courseResponse;
    }
}
