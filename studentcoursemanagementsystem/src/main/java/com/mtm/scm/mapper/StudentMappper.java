package com.mtm.scm.mapper;

import com.mtm.scm.dto.response.StudentResponse;
import com.mtm.scm.entity.StudentEntity;
import com.mtm.scm.dto.request.CreateStudentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMappper {

    StudentEntity toEntity(CreateStudentRequest craeCreateStudentRequest);

    StudentResponse toDto(StudentEntity studentEntity);
}
