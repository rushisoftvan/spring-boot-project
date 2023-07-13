package com.mtm.scm.controller;

import com.mtm.scm.dto.request.PageStudentListRequest;
import com.mtm.scm.dto.request.UpdateStudentRequest;
import com.mtm.scm.dto.response.ApiResponse;
import com.mtm.scm.dto.response.PagedStudentResponse;
import com.mtm.scm.service.StudentService;
import com.mtm.scm.dto.request.CreateStudentRequest;
import com.mtm.scm.dto.response.StudentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/studentapi")
@RequiredArgsConstructor
@Validated
@Slf4j
public class StudentController {

    private final StudentService studentService;
    @PostMapping("/students")
    public ApiResponse<StudentResponse> saveStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest){
        log.debug("<<<<<<<<< saveStudent()");
        StudentResponse studentResponse = this.studentService.addStudent(createStudentRequest);
        log.debug("saveStudent() >>>>>>>");
        ApiResponse.ApiResponseBuilder<StudentResponse> builder = ApiResponse.builder();
     return  builder.Data(studentResponse).statusCode(HttpStatus.CREATED.value()).build();
    }

    @GetMapping("/students/{id}")
    public ApiResponse<StudentResponse> getStudentById(@PathVariable("id")  @Positive(message= "id should be greater than zero") @NotNull(message="id should not be null or empty") Integer id){
        log.debug("<<<<<<<<< getStudentById()");
        StudentResponse studentResponse = this.studentService.fetchStudentById(id);
        log.debug("studentResponse id :: {}",studentResponse.getId());
        log.debug("getStudentById() >>>>>>>");
        ApiResponse.ApiResponseBuilder<StudentResponse> builder = ApiResponse.builder();
        return  builder.Data(studentResponse).statusCode(HttpStatus.OK.value()).build();
    }

    @PutMapping("/students/{id}")
    public ApiResponse<StudentResponse> updateStudent(@PathVariable("id") Integer id, @Valid @RequestBody UpdateStudentRequest updateStudentRequest){
        log.debug("<<<<<<<<< updateStudent()");
        StudentResponse studentResponse = this.studentService.updateStudent(id, updateStudentRequest);
        log.debug("updateStudent() >>>>>>>");
        ApiResponse.ApiResponseBuilder<StudentResponse> builder = ApiResponse.builder();
        return  builder.Data(studentResponse).statusCode(HttpStatus.OK.value()).build();
    }

    @PostMapping("/pageStudents")
    public ApiResponse<PagedStudentResponse> getStudentList(@Valid @RequestBody PageStudentListRequest pageStudentListRequest){
        log.debug("<<<<<<<<< getStudentList()");
        PagedStudentResponse pagedStudentResponse = this.studentService.pageStudentList(pageStudentListRequest);
        ApiResponse.ApiResponseBuilder<PagedStudentResponse> builder = ApiResponse.builder();
        log.debug("getStudentList() >>>>>>>");
                                                                                                                                                                                                                                                                                                return  builder.Data(pagedStudentResponse).statusCode(HttpStatus.OK.value()).build();
                                                                                                                                                                                                                                                                                            }
}
