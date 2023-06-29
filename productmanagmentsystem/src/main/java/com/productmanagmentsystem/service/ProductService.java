package com.productmanagmentsystem.service;

import com.productmanagmentsystem.dto.request.CreateProductRequest;
import com.productmanagmentsystem.dto.request.ProductPageRequest;
import com.productmanagmentsystem.dto.request.UpdateProductRequest;
import com.productmanagmentsystem.dto.response.ProductPagedResponse;
import com.productmanagmentsystem.dto.response.ProductResponse;
import com.productmanagmentsystem.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    public ProductResponse saveProduct(CreateProductRequest createProductRequest);

    public ProductResponse  fetchProductById(Integer productId);

    public String  deleteProduct(Integer productId);

    public ProductResponse updateProduct(Integer id,UpdateProductRequest updateProductRequest);

    List<ProductResponse> getPagedProductList();
}
