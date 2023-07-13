package com.mtm.scm.controller;

import com.mtm.scm.dto.request.CoursePageRequest;
import com.mtm.scm.dto.request.UpdateCourseRequest;
import com.mtm.scm.dto.response.ApiResponse;
import com.mtm.scm.dto.response.PagedCourseResponse;
import com.mtm.scm.service.CourseService;
import com.mtm.scm.dto.request.CreateCourseRequest;
import com.mtm.scm.dto.response.CourseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/courseapi")
@Validated
@RequiredArgsConstructor
@Slf4j
public class CourseController {

   private final CourseService courseService;

   @PostMapping("/courses")
   public ApiResponse<CourseResponse> saveCourse(@Valid @RequestBody CreateCourseRequest createCourseRequest){
       log.debug("<<<<<<<<< saveCourse()");
       CourseResponse courseResponse = this.courseService.saveCourse(createCourseRequest);
       log.debug("saveCourse() >>>>>>>");
       ApiResponse.ApiResponseBuilder<CourseResponse> builder = ApiResponse.builder();
       return builder.Data(courseResponse).statusCode(HttpStatus.CREATED.value()).build();
   }

   @GetMapping("/courses/{id}")
   public ApiResponse<CourseResponse> getCourseById(@PathVariable("id")  @Positive(message= "id should be greater than zero") @NotNull(message="id should not be null or empty") Integer id){
       log.debug("<<<<<<<<< getCourseById()");
       CourseResponse courseResponse = this.courseService.fetchCourseById(id);
       log.debug("<<<<<<<<< getCourseById()");
       ApiResponse.ApiResponseBuilder<CourseResponse> builder = ApiResponse.builder();
       return builder.Data(courseResponse).statusCode(HttpStatus.OK.value()).build();
   }

   @PutMapping("/courses/{id}")
   public ApiResponse<CourseResponse> updateCourse(@PathVariable("id")  @Positive(message= "id should be greater than zero") @NotNull(message="id should not be null or empty") Integer id , @Valid @RequestBody UpdateCourseRequest updateCourse){
       log.debug("<<<<<<<<< updateCourse()");
       CourseResponse courseResponse = this.courseService.updateCourse(id, updateCourse);
       ApiResponse.ApiResponseBuilder<CourseResponse> builder = ApiResponse.builder();
       log.debug("<<<<<<<<< updateCourse()");
       return builder.Data(courseResponse).statusCode(HttpStatus.OK.value()).build();
   }


   @PostMapping("/pageCourse")
   public ApiResponse<PagedCourseResponse> pageListOfCourse(@Valid @RequestBody CoursePageRequest coursePageRequest){
       log.debug("<<<<<<<<< pageListOfCourse()");
       PagedCourseResponse pagedCourseResponse = this.courseService.fetchCourseBasedOnPage(coursePageRequest);
       log.debug("<<<<<<<<< pageListOfCourse()");
       ApiResponse.ApiResponseBuilder<PagedCourseResponse> builder = ApiResponse.builder();
       return builder.Data(pagedCourseResponse).statusCode(HttpStatus.OK.value()).build();
   }



}
