package in.manytomany.studentcoursemanagementsystem.mapper;

import in.manytomany.studentcoursemanagementsystem.dto.request.CreateStudentRequest;
import in.manytomany.studentcoursemanagementsystem.dto.response.StudentResponse;
import in.manytomany.studentcoursemanagementsystem.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMappper {

    StudentEntity toEntity(CreateStudentRequest craeCreateStudentRequest);

    StudentResponse toDto(StudentEntity studentEntity);
}
