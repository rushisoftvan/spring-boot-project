package com.productmanagmentsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPageRequest {

    private  Integer pageNumber =1;

    private Integer pageSize=5;

}
