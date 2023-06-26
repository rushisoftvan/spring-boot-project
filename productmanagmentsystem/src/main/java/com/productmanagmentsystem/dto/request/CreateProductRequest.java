package com.productmanagmentsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {


    @NotEmpty(message="Product Name should not be Empty or Null")
    private String productName;


    @NotEmpty(message="Product Description  should not be Empty or Null")
    private String productDescription;

    @NotNull(message = "Product Price should not be null")
    private Integer productPrice;
}
