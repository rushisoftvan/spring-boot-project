package com.mtm.scm.service;

import com.mtm.scm.dto.request.CreateStudentRequest;
import com.mtm.scm.dto.response.StudentResponse;
import com.mtm.scm.entity.StudentEntity;
import com.mtm.scm.exception.CustomException;
import com.mtm.scm.exception.RecordNotFoundException;
import com.mtm.scm.mapper.StudentMappper;
import com.mtm.scm.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMappper studentMappper;
      public StudentResponse addStudent(CreateStudentRequest createStudentRequest){
          checkCreateStudentRequest(createStudentRequest);
          StudentEntity studentEntity = this.studentMappper.toEntity(createStudentRequest);
          StudentEntity savedEntity = this.studentRepository.save(studentEntity);
          return this.studentMappper.toDto(studentEntity);


      }

      public StudentResponse fetchStudentById(Integer id){
          StudentEntity studentEntity = fetchStudentEntityById(id);
         return this.studentMappper.toDto(studentEntity);
                  
          
      }

    public StudentEntity fetchStudentEntityById(Integer id)  {
        Optional<StudentEntity> optionalStudent = this.studentRepository.findById(id);
        if(optionalStudent.isPresent()){
            return optionalStudent.get();
        }
        //throw new RecordNotFoundException("Record Not Found for this id " + id);
        throw new RecordNotFoundException("Record Not Found for this id " + id);
    }


    private static void checkCreateStudentRequest(CreateStudentRequest createStudentRequest) {
        if(Objects.isNull(createStudentRequest)){
          throw new CustomException("createstudentrequest should not be null");
        }
    }

    public void fetchStudentEnroldInCourse(Integer cousreId){

    }
}
