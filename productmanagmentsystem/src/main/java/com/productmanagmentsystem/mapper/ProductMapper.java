package com.productmanagmentsystem.mapper;

import com.productmanagmentsystem.dto.request.CreateProductRequest;
import com.productmanagmentsystem.dto.response.ProductResponse;
import com.productmanagmentsystem.entity.ProductEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ProductMapper {

         public ProductEntity toEntity(CreateProductRequest createProductRequest){
             log.debug("<<<<<<<<< toEntity() ");
             ProductEntity productEntity = new ProductEntity();
             productEntity.setName(createProductRequest.getProductName());
             productEntity.setPrice(createProductRequest.getProductPrice());
             productEntity.setDescription(createProductRequest.getProductDescription());
             log.debug("toEntity() >>>>>>>>");
             return productEntity;
         }

         public ProductResponse toDto(ProductEntity productEntity){
             log.debug("<<<<<<<<< toDto() ");
             ProductResponse productResponse = new ProductResponse();
             productResponse.setProductId(productEntity.getId());
            productResponse.setProductName(productEntity.getName());
             productResponse.setProductPrice(productEntity.getPrice());
             productResponse.setProductDescription(productEntity.getDescription());
             productResponse.setStatus(productEntity.getStatus());
             log.debug("toDto() >>>>>>>>");
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
