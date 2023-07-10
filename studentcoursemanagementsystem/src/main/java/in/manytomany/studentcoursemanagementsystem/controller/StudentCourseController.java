package in.manytomany.studentcoursemanagementsystem.controller;

import in.manytomany.studentcoursemanagementsystem.dto.StudentCourseDto;
import in.manytomany.studentcoursemanagementsystem.dto.request.BuyCourseRequest;
import in.manytomany.studentcoursemanagementsystem.dto.response.ApiResponse;
import in.manytomany.studentcoursemanagementsystem.dto.response.StudentCourseResponse;
import in.manytomany.studentcoursemanagementsystem.service.StudentCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/StudentCourse/api")
@RequiredArgsConstructor
@Validated

public class StudentCourseController {

    private final StudentCourseService studentCourseService;

    @PostMapping("/studentcourse")
    public ApiResponse<StudentCourseResponse> buyCousreByStudent(@Valid @RequestBody BuyCourseRequest buyCourseRequest){

        StudentCourseResponse studentCourseResponse = this.studentCourseService.buyCourseByStudent(buyCourseRequest);
        ApiResponse.ApiResponseBuilder<StudentCourseResponse> builder = ApiResponse.builder();
        return builder.Data(studentCourseResponse).statusCode(HttpStatus.CREATED.value()).build();

    }

         @GetMapping("/studentcourse/{id}")
        public ApiResponse<List<StudentCourseDto>> getStudentstoEnrollInCourse(@PathVariable("id") @Positive(message= "id should be greater than zero") @NotNull(message = "course id should not be null") Integer courseId){

            List<StudentCourseDto> studentCourseResponses = this.studentCourseService.fetchStudentstoEnrollInCourse(courseId);
            ApiResponse.ApiResponseBuilder<List<StudentCourseDto>> builder = ApiResponse.builder();
            return  builder.Data(studentCourseResponses).statusCode(HttpStatus.OK.value()).build();

        }

}
