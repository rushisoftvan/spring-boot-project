package com.productmanagmentsystem.mapper;

import com.productmanagmentsystem.dto.request.CreateProductRequest;
import com.productmanagmentsystem.dto.response.ProductResponse;
import com.productmanagmentsystem.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

         public ProductEntity toEntity(CreateProductRequest createProductRequest){
             ProductEntity productEntity = new ProductEntity();
             productEntity.setName(createProductRequest.getProductName());
             productEntity.setPrice(createProductRequest.getProductPrice());
             productEntity.setDescription(createProductRequest.getProductDescription());

             return productEntity;
         }

         public ProductResponse toDto(ProductEntity productEntity){

             ProductResponse productResponse = new ProductResponse();
             productResponse.setProductId(productEntity.getId());
            productResponse.setProductName(productEntity.getName());
             productResponse.setProductPrice(productEntity.getPrice());
             productResponse.setProductDescription(productEntity.getDescription());
             productResponse.setStatus(productEntity.getStatus());
             return productResponse;
         }




         public List<ProductResponse> toDtoList(List<ProductEntity> productsEntities){
             List<ProductResponse> products = new ArrayList();

             for(ProductEntity productEntity : productsEntities){
                 ProductResponse productResponse = toDto(productEntity);
                 products.add(productResponse);
             }
             return products;
         }



}
