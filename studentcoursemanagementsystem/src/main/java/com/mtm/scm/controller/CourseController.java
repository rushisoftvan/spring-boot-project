package in.manytomany.studentcoursemanagementsystem.controller;

import in.manytomany.studentcoursemanagementsystem.dto.request.CreateCourseRequest;
import in.manytomany.studentcoursemanagementsystem.dto.response.ApiResponse;
import in.manytomany.studentcoursemanagementsystem.dto.response.CourseResponse;
import in.manytomany.studentcoursemanagementsystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/courseapi")
@Validated
@RequiredArgsConstructor
public class CourseController {

   private final CourseService courseService;

   @PostMapping("/courses")
   public ApiResponse<CourseResponse> saveCourse(@Valid @RequestBody CreateCourseRequest createCourseRequest){
       CourseResponse courseResponse = this.courseService.saveCourse(createCourseRequest);
       ApiResponse.ApiResponseBuilder<CourseResponse> builder = ApiResponse.builder();
       return builder.Data(courseResponse).statusCode(HttpStatus.CREATED.value()).build();
   }

   @GetMapping("/courses/{id}")
   public ApiResponse<CourseResponse> getCourseById(Integer id){
       CourseResponse courseResponse = this.courseService.fetchCourseById(id);
       ApiResponse.ApiResponseBuilder<CourseResponse> builder = ApiResponse.builder();
       return builder.Data(courseResponse).statusCode(HttpStatus.OK.value()).build();
   }
}
