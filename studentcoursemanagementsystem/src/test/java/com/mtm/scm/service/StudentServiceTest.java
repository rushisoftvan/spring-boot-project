package in.manytomany.studentcoursemanagementsystem.service;

import in.manytomany.studentcoursemanagementsystem.dto.request.CreateStudentRequest;
import in.manytomany.studentcoursemanagementsystem.entity.StudentEntity;
import in.manytomany.studentcoursemanagementsystem.enums.Status;
import in.manytomany.studentcoursemanagementsystem.mapper.StudentMappper;
import in.manytomany.studentcoursemanagementsystem.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Test
    public void testaddStudent(){

        StudentRepository studentRepoMock = Mockito.mock(StudentRepository.class);


        CreateStudentRequest createStudentRequest = new CreateStudentRequest();
        createStudentRequest.setName("sachin");
        createStudentRequest.setAge(22);

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(1);
        studentEntity.setName("sachin");
        studentEntity.setAge(22);
        studentEntity.setStatus(Status.ACTIVE);

        
    }


}