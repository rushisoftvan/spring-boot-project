package com.productmanagmentsystem.dto.request;

import com.productmanagmentsystem.enums.StatusEnum;
import com.productmanagmentsystem.valiadtion.ValidateStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProductRequest {

    @NotEmpty(message="Product Name should not be null or Empty")
    private String productName;

    @NotEmpty(message="Product Description should not be null or Empty")
    private String productDescription;

    @NotNull(message = "Product Price should not be null")
    private Integer productPrice;

    @ValidateStatus
    private StatusEnum staus;
}
