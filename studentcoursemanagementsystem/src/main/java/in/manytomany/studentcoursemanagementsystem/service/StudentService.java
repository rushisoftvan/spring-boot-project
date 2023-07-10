package in.manytomany.studentcoursemanagementsystem.service;

import in.manytomany.studentcoursemanagementsystem.dto.request.CreateStudentRequest;
import in.manytomany.studentcoursemanagementsystem.dto.response.StudentResponse;
import in.manytomany.studentcoursemanagementsystem.entity.StudentEntity;
import in.manytomany.studentcoursemanagementsystem.exception.CustomException;
import in.manytomany.studentcoursemanagementsystem.exception.RecordNotFoundException;
import in.manytomany.studentcoursemanagementsystem.mapper.StudentMappper;
import in.manytomany.studentcoursemanagementsystem.repository.StudentRepository;
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
