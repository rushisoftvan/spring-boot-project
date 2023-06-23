package com.productmanagmentsystem.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.productmanagmentsystem.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

    private Integer productId;

    private String productName;

    private String productDescription;

    private Integer productPrice;

    private StatusEnum status;

    private LocalDateTime productCreatedDateTime;

    private LocalDateTime productUpdatedDateTime;
}

