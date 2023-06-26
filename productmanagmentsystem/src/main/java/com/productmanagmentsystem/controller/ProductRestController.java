package com.productmanagmentsystem.controller;

import com.productmanagmentsystem.dto.request.CreateProductRequest;
import com.productmanagmentsystem.dto.request.UpdateProductRequest;
import com.productmanagmentsystem.dto.response.ApiResponse;
import com.productmanagmentsystem.dto.response.ProductResponse;
import com.productmanagmentsystem.service.ProductServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ProductRestController {


    private final ProductServiceImp productServiceImp;

    @PostMapping("/products")
    public ApiResponse saveProduct(@RequestBody @Valid CreateProductRequest createProductRequest) {
        log.debug("<<<<<<<<< saveProduct() ");
        ProductResponse productResponse = this.productServiceImp.saveProduct(createProductRequest);
        log.info("Product create sucessfully with id {}", productResponse.getProductId());
        log.debug("saveProduct() >>>>>>>>");
        return new ApiResponse(productResponse, HttpStatus.CREATED.value());

    }

    @GetMapping("products/{id}")
    public ApiResponse getProductById(@Positive(message = "ProductId should not be null or zero") @PathVariable("id") Integer productId) {
        log.debug("<<<<<<<<< getProductById() ");
        ProductResponse productResponse = this.productServiceImp.fetchProductById(productId);
        log.debug("getProductById() >>>>>>>>");
        return new ApiResponse(productResponse, HttpStatus.OK.value());
    }

    @GetMapping(value = "/pageProductList" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse getPagedProductList() {
        log.debug("<<<<<<<<< getPagedProductList() ");
        List<ProductResponse> productPagedResponse = this.productServiceImp.getPagedProductList();
        return  new ApiResponse(productPagedResponse,HttpStatus.OK.value());
    }

    @DeleteMapping("/products/{id}")
    public ApiResponse deleteProductById(@PathVariable("id") Integer productId) {
        log.debug("<<<<<<<<< deleteProductById() ");
        this.productServiceImp.deleteProduct(productId);
        log.debug("deleteProductById()>>>>>>>> ");
        return new ApiResponse(true, HttpStatus.OK.value());
    }

    @PutMapping("products/{id}")
    public ApiResponse updateProduct(@PathVariable Integer id,@Valid @RequestBody UpdateProductRequest updateProductRequest) {
        log.debug("<<<<<<<<< updateProduct() ");
        ProductResponse productResponse = this.productServiceImp.updateProduct(id, updateProductRequest);
        log.debug("updateProduct()>>>>>>>> ");
        return new ApiResponse(productResponse, HttpStatus.OK.value());
    }

}
