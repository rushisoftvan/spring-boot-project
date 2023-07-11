package com.mtm.scm.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagedStudentResponse {

    private List<StudentResponse> studentResponses;

    private long totalRecords;

    private boolean isFirstPage;

    private boolean isLastPage;

    private boolean hasPrevious;

    private boolean hasNext;
}
