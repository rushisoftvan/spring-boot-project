package com.productmanagmentsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProductRequest {


    private String productName;

    private String productDescription;

    private Integer productPrice;
}
