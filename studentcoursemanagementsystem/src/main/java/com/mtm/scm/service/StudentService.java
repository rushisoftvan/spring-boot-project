package com.mtm.scm.service;

import com.mtm.scm.dto.request.CreateStudentRequest;
import com.mtm.scm.dto.request.PageStudentListRequest;
import com.mtm.scm.dto.request.UpdateStudentRequest;
import com.mtm.scm.dto.response.PagedStudentResponse;
import com.mtm.scm.dto.response.StudentResponse;
import com.mtm.scm.entity.StudentEntity;
import com.mtm.scm.exception.CustomException;
import com.mtm.scm.exception.RecordNotFoundException;
import com.mtm.scm.mapper.StudentMappper;
import com.mtm.scm.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMappper studentMappper;
      public StudentResponse addStudent(CreateStudentRequest createStudentRequest){
          log.debug("<<<<<<<<< addStudent()");
          checkCreateStudentRequest(createStudentRequest);
          StudentEntity studentEntity = this.studentMappper.toEntity(createStudentRequest);
          StudentEntity savedEntity = this.studentRepository.save(studentEntity);
          log.debug("addStudent() >>>>>>>");
          return this.studentMappper.toDto(studentEntity);
      }

      public StudentResponse fetchStudentById(Integer id){
          log.debug("<<<<<<<<< fetchStudentById()");
          StudentEntity studentEntity = fetchStudentEntityById(id);
          StudentResponse studentResponse = this.studentMappper.toDto(studentEntity);
          log.debug("fetchStudentById() >>>>>>>");
          return studentResponse;
      }

    public StudentEntity fetchStudentEntityById(Integer id)  {
        log.debug("<<<<<<<<< fetchStudentEntityById()");
        Optional<StudentEntity> optionalStudent = this.studentRepository.findById(id);
        if(optionalStudent.isPresent()){
            log.debug("fetchStudentEntityById() >>>>>>>");
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

    @Transactional
    public StudentResponse updateStudent(Integer id, UpdateStudentRequest updateStudentRequest)
    {     log.debug("<<<<<<<<< updateStudent()");
        StudentEntity studentEntity = fetchStudentEntityById(id);
        studentEntity.setAge(updateStudentRequest.getAge());
        studentEntity.setName(updateStudentRequest.getName());
        studentEntity.setStatus(updateStudentRequest.getStatus());
        StudentEntity updatedStudent = this.studentRepository.save(studentEntity);
        log.debug("updateStudent() >>>>>>>");
        return this.studentMappper.toDto(updatedStudent);
    }

    public PagedStudentResponse pageStudentList(PageStudentListRequest pageStudentListRequest){
        Integer pageNo=pageStudentListRequest.getPageNo()-1;
        Integer pageSize =pageStudentListRequest.getPageSize();
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<StudentEntity> studentPage= this.studentRepository.findAll(pageable);
        List<StudentResponse> studentResponses = this.studentMappper.listOfEntityToDto(studentPage.getContent());
        PagedStudentResponse pagedStudentResponse = new PagedStudentResponse();
        pagedStudentResponse.setStudentResponses(studentResponses);
        pagedStudentResponse.setFirstPage(studentPage.isFirst());
        pagedStudentResponse.setLastPage(studentPage.isLast());
        pagedStudentResponse.setTotalRecords(studentPage.getTotalElements());
       return pagedStudentResponse;
    }
}
