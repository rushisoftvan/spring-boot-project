package in.manytomany.studentcoursemanagementsystem.controller;

import in.manytomany.studentcoursemanagementsystem.dto.request.CreateStudentRequest;
import in.manytomany.studentcoursemanagementsystem.dto.response.ApiResponse;
import in.manytomany.studentcoursemanagementsystem.dto.response.StudentResponse;
import in.manytomany.studentcoursemanagementsystem.service.StudentService;
import lombok.RequiredArgsConstructor;
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
public class StudentController {

    private final StudentService studentService;
    @PostMapping("/students")
    public ApiResponse<StudentResponse> save(@Valid @RequestBody CreateStudentRequest createStudentRequest){
        StudentResponse studentResponse = this.studentService.addStudent(createStudentRequest);
        ApiResponse.ApiResponseBuilder<StudentResponse> builder = ApiResponse.builder();
     return  builder.Data(studentResponse).statusCode(HttpStatus.CREATED.value()).build();
    }

    @GetMapping("/students/{id}")
    public ApiResponse<StudentResponse> getStudentById(@PathVariable("id")  @Positive(message= "id should be greater than zero") @NotNull(message="id should not be null or empty") Integer id){
        StudentResponse studentResponse = this.studentService.fetchStudentById(id);
        ApiResponse.ApiResponseBuilder<StudentResponse> builder = ApiResponse.builder();
        return  builder.Data(studentResponse).statusCode(HttpStatus.OK.value()).build();
    }
}
