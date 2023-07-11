package com.mtm.scm.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagedCourseResponse {

    private List<CourseResponse> courseResponseList;


    private long totalRecords;

    private boolean isFirstPage;

    private boolean isLastPage;

    private boolean hasPrevious;

    private boolean hasNext;

}
