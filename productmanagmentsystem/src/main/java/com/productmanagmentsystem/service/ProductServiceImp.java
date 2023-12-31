package com.productmanagmentsystem.service;

import com.productmanagmentsystem.dto.request.CreateProductRequest;
import com.productmanagmentsystem.dto.request.UpdateProductRequest;
import com.productmanagmentsystem.dto.response.ProductResponse;
import com.productmanagmentsystem.entity.ProductEntity;
import com.productmanagmentsystem.enums.StatusEnum;
import com.productmanagmentsystem.exception.ProductNotFoundException;
import com.productmanagmentsystem.mapper.ProductMapper;
import com.productmanagmentsystem.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;


    @Override
    public ProductResponse saveProduct(CreateProductRequest createProductRequest) {
        log.debug("<<<<<<<<< createProduct() ");
        checkProductNull(createProductRequest);
        ProductEntity productEntity = this.productMapper.toEntity(createProductRequest);
        ProductEntity save = this.productRepository.save(productEntity);
        log.debug("createProduct() >>>>>>>>");
        return this.productMapper.toDto(save);
    }

    @Override

    public ProductResponse fetchProductById(Integer productId) {
        log.debug("<<<<<<<<< fetchProductById");
        ProductEntity productEntity = getProductEntityById(productId);
        log.debug("fetchProductById() >>>>>>>");
        //System.out.println("productEntity ::::::::::::::::::::::::= " + productEntity.toString());
        return this.productMapper.toDto(productEntity);
    }

    @Override
    public String deleteProduct(Integer productId) {
        log.debug("<<<<<<<<< deleteProduct()");
        log.debug("deleteProduct() >>>>>>>");
        ProductEntity product = getProductEntityById(productId);
        product.setStatus(StatusEnum.IN_ACTIVE);
        productRepository.save(product);
        log.debug("deleteProduct() >>>>>>>");
        return "data is deleted for id : "+ productId;
    }

    @Override
    public ProductResponse updateProduct(Integer id, UpdateProductRequest updateProductRequest) {
        if(updateProductRequest==null){
            throw new NullPointerException("updateProductList should not be null");

        }
        log.debug("<<<<<<<<< updateProduct()");
        ProductEntity dbProductData = getProductEntityById(id);
        dbProductData.setName(updateProductRequest.getProductName());
        dbProductData.setPrice(updateProductRequest.getProductPrice());
        dbProductData.setDescription(updateProductRequest.getProductDescription());
        dbProductData.setStatus(updateProductRequest.getStaus());
        ProductEntity updatedproductEntity = this.productRepository.save(dbProductData);
        log.debug("updateProduct() >>>>>>>");
        return this.productMapper.toDto(updatedproductEntity);
    }

    @Override
    public List<ProductResponse> getPagedProductList() {
        List<ProductEntity> allProductList = productRepository.findAll();
        if(allProductList.isEmpty()){
            throw new ProductNotFoundException("No Product Available");
        }
        List<ProductResponse> collect = allProductList.stream()
                .map(productEntity -> new ProductResponse(null, productEntity.getName(), productEntity.getDescription(), productEntity.getPrice(), productEntity.getStatus(), productEntity.getCreatedDateTime(), productEntity.getUpdatedDateTime())).collect(Collectors.toList());
        collect.forEach(System.out::println);
        return collect;
    }


    private ProductEntity getProductEntityById(Integer productId) {
        log.debug("<<<<<<<<< getProductEntityById()");
        Optional<ProductEntity> product = this.productRepository.findById(productId);
        if (product.isPresent()) {
            log.debug("getProductEntityById() >>>>>>>");
            return product.get();
        } else {
            //
            // throw new ProductNotFoundException(String.format("product does not exists with id : %s", productId));
            throw new ProductNotFoundException(String.format("product does not exists with id : %s", productId));
        }
    }


    private static void checkProductNull(CreateProductRequest createProductRequest) {
        if (createProductRequest == null) {
            throw new NullPointerException("product object should not be null");
        }
    }
}
