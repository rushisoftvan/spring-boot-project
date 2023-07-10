package in.manytomany.studentcoursemanagementsystem.mapper;

import in.manytomany.studentcoursemanagementsystem.dto.request.CreateCourseRequest;
import in.manytomany.studentcoursemanagementsystem.dto.response.CourseResponse;
import in.manytomany.studentcoursemanagementsystem.entity.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper {

    CourseEntity toEntity(CreateCourseRequest createCourseRequest);
    CourseResponse toDto(CourseEntity courseEntity);
}
