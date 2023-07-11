package com.mtm.scm.mapper;

import com.mtm.scm.dto.request.CreateCourseRequest;
import com.mtm.scm.dto.response.CourseResponse;
import com.mtm.scm.entity.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper {

    CourseEntity toEntity(CreateCourseRequest createCourseRequest);
    CourseResponse toDto(CourseEntity courseEntity);

    List<CourseResponse> toListOfCourseResponse(List<CourseEntity> courses);
}
