package com.mtm.scm.service;

import com.mtm.scm.dto.request.CoursePageRequest;
import com.mtm.scm.dto.request.CreateCourseRequest;
import com.mtm.scm.dto.request.UpdateCourseRequest;
import com.mtm.scm.dto.response.CourseResponse;
import com.mtm.scm.dto.response.PagedCourseResponse;
import com.mtm.scm.entity.CourseEntity;
import com.mtm.scm.exception.CustomException;
import com.mtm.scm.exception.RecordNotFoundException;
import com.mtm.scm.mapper.CourseMapper;
import com.mtm.scm.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
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
public class CourseService {


    private final CourseRepository courseRepository;


    private final CourseMapper courseMapper;


    public CourseResponse saveCourse(CreateCourseRequest createCourseRequest) {
        checkCreateCourseRequest(createCourseRequest);
        CourseEntity courseEntity = this.courseMapper.toEntity(createCourseRequest);
        CourseEntity savedEntity = this.courseRepository.save(courseEntity);
        return this.courseMapper.toDto(savedEntity);
    }

    private static void checkCreateCourseRequest(CreateCourseRequest createCourseRequest) {
        if (Objects.isNull(createCourseRequest)) {
            throw new CustomException("createcourse request should not be null");
        }
    }

    public CourseResponse fetchCourseById(Integer id) {
        CourseEntity courseEntityById = getCourseEntityById(id);
        return this.courseMapper.toDto(courseEntityById);
    }

    public CourseEntity getCourseEntityById(Integer id) {
        Optional<CourseEntity> dbCourseData = this.courseRepository.findById(id);
        if (dbCourseData.isPresent()) {
            return dbCourseData.get();
        }
        throw new RecordNotFoundException("Record not found for thid id :" + id);
    }


    @Transactional
    public CourseResponse updateCourse(Integer id, UpdateCourseRequest updateCourse)
    {
        if(Objects.isNull(id) || Objects.isNull(updateCourse)){
           throw new CustomException("id or update course requset should not be null");
        }
        CourseEntity courseEntityById = this.getCourseEntityById(id);
        courseEntityById.setName(updateCourse.getName());
        courseEntityById.setPrice(updateCourse.getPrice());
        courseEntityById.setStatus(updateCourse.getStatus());
        CourseEntity updatedStudent = this.courseRepository.save(courseEntityById);
       return  this.courseMapper.toDto(updatedStudent);
    }
    public PagedCourseResponse fetchCourseBasedOnPage(CoursePageRequest coursePageRequest){

        int pageNo=coursePageRequest.getPageNo()-1;
        int pageSize=coursePageRequest.getPageSize();
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<CourseEntity> pageOfCourse = this.courseRepository.findAll(pageable);
        List<CourseResponse> listOfCourseResponse = this.courseMapper.toListOfCourseResponse(pageOfCourse.getContent());
        PagedCourseResponse pagedCourseResponse = new PagedCourseResponse();
        pagedCourseResponse.setCourseResponseList(listOfCourseResponse);
        pagedCourseResponse.setFirstPage(pageOfCourse.isFirst());
        pagedCourseResponse.setLastPage(pageOfCourse.isLast());
        pagedCourseResponse.setTotalRecords(pageOfCourse.getTotalElements());
        return pagedCourseResponse;

    }




}
