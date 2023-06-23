package com.productmanagmentsystem.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ProductPagedResponse {
    private List<ProductResponse> productList;

    private long totalRecords;

    private boolean isFirstPage;

    private boolean isLastPage;

    private boolean hasPrevious;

    private boolean hasNext;

}
