package com.mtm.scm.service;

import com.mtm.scm.entity.StudentEntity;
import com.mtm.scm.enums.Status;
import com.mtm.scm.repository.StudentRepository;
import com.mtm.scm.dto.request.CreateStudentRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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