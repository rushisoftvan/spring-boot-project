package com.productmanagmentsystem.service;

import com.productmanagmentsystem.dto.request.CreateProductRequest;
import com.productmanagmentsystem.dto.request.UpdateProductRequest;
import com.productmanagmentsystem.dto.response.ProductResponse;
import com.productmanagmentsystem.entity.ProductEntity;
import com.productmanagmentsystem.enums.StatusEnum;
import com.productmanagmentsystem.exception.ProductNotFoundException;
import com.productmanagmentsystem.mapper.ProductMapper;
import com.productmanagmentsystem.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.swing.text.html.Option;
import java.lang.management.MonitorInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    @InjectMocks
    ProductServiceImp productServiceImp;

    @MockBean
    private ProductRepository productRepository;


    @Test
    public void testSaveProduct1() {
        ProductRepository productRepoMock = Mockito.mock(ProductRepository.class);
        ProductMapper productMapper = new ProductMapper();
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setProductName("Mobile");
        createProductRequest.setProductDescription("This is good product");
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1);
        productEntity.setName("Mobile");
        Mockito.when(productRepoMock.save(Mockito.any(ProductEntity.class))).thenReturn(productEntity);
        ProductServiceImp productServiceImp = new ProductServiceImp(productRepoMock, productMapper);
        ProductResponse productResponse = productServiceImp.saveProduct(createProductRequest);
        assertEquals("Mobile", productResponse.getProductName());


    }

    @Test
    public void testSaveProduct2() {
        ProductRepository productRepoMock = Mockito.mock(ProductRepository.class);
        ProductMapper productMapper = new ProductMapper();
        ProductServiceImp productServiceImp = new ProductServiceImp(productRepoMock, productMapper);
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> productServiceImp.saveProduct(null));
        assertEquals("product object should not be null", nullPointerException.getMessage());
    }

    @Test
    public void testFetchProductById() {
        //request

        Integer productId = 1;
        //Mocking
        ProductEntity productEntity = new ProductEntity();
        productEntity.setStatus(StatusEnum.IN_ACTIVE);
        productEntity.setDescription("desc");
        productEntity.setPrice(100);
        productEntity.setName("name");

        //Mockito.when(this.modelMapperMock.toDto(Mockito.any(ProductEntity.class), Mockito.(ProductResponse.class))).thenReturn(Mockito.mock(ProductResponse.class));
        //Mock
        Mockito.when(this.productRepository.findById(productId)).thenReturn(Optional.of(productEntity));
        //Test
        ProductResponse productResponse = productServiceImp.fetchProductById(productId);
        Mockito.verify(this.productRepository, Mockito.times(1)).findById(productId);

        assertEquals("name", productResponse.getProductName());


        //Verify
        // Mockito.verify(productRepository,Mockito.times(1)).findById(productId);
    }

    @Test
    public void testGetProductListOne() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1);
        productEntity.setStatus(StatusEnum.ACTIVE);
        productEntity.setDescription("desc");
        productEntity.setPrice(100);
        productEntity.setName("name");

        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(productEntity));
        List<ProductResponse> pagedProductList = productServiceImp.getPagedProductList();
        assertEquals(1, pagedProductList.size());
    }

    @Test
    public void testGetProductListTwo() {
        List<ProductEntity> productList = new ArrayList();
        Mockito.when(productRepository.findAll()).thenReturn(productList);
        ProductNotFoundException productNotFoundException = assertThrows(ProductNotFoundException.class, () -> productServiceImp.getPagedProductList());
        assertEquals("No Product Available", productNotFoundException.getMessage());

    }


    @Test
    public void testUpdateProductOne() {
        Integer id = 1;
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1);
        productEntity.setStatus(StatusEnum.ACTIVE);
        productEntity.setDescription("desc");
        productEntity.setPrice(100);
        productEntity.setName("Mobile");

        UpdateProductRequest updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setProductName("Table");
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(productEntity));
        productEntity.setName(updateProductRequest.getProductName());
        Mockito.when(productRepository.save(Mockito.any(ProductEntity.class))).thenReturn(productEntity);
        ProductResponse productResponse = productServiceImp.updateProduct(id, updateProductRequest);
        assertEquals("Table", productResponse.getProductName());

    }

    @Test
    public void testUpdateProductIdNull() {
        Integer id = null;

        UpdateProductRequest updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setProductName("Table");
        updateProductRequest.setProductPrice(100);
        updateProductRequest.setProductDescription("desc");
        updateProductRequest.setStaus(StatusEnum.ACTIVE);


        //Mocking
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1);
        productEntity.setStatus(StatusEnum.ACTIVE);
        productEntity.setDescription("desc");
        productEntity.setPrice(100);
        productEntity.setName("Mobile");


        Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());

        ProductNotFoundException actualResponce = assertThrows(ProductNotFoundException.class,
                () -> productServiceImp.updateProduct(id, updateProductRequest));

        Mockito.verify(productRepository, Mockito.times(1)).findById(id);
        assertNotNull(actualResponce);


    }


    @Test
    public void testUpdateProductUpdateRequestNull(){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("Table");
        Mockito.when(this.productRepository.findById(1)).thenReturn(Optional.of(productEntity));
        assertThrows(NullPointerException.class,()->productServiceImp.updateProduct(1,null));
    }


    @Test
    public void testdeleteProduct(){
      Integer id=1;
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("Table");
        productEntity.setPrice(100);

      Mockito.when(this.productRepository.findById(id)).thenReturn(Optional.of(productEntity));
      productEntity.setStatus(StatusEnum.IN_ACTIVE);
      Mockito.when(this.productRepository.save(productEntity)).thenReturn(productEntity);


        String deletedMessage = productServiceImp.deleteProduct(1);
        //Mockito.when(this.productRepository)
        assertEquals("data is deleted for id : "+ id,deletedMessage);

    }

      @Test
      public void   testDeleteProductIdNull(){

          ProductNotFoundException productNotFoundException = assertThrows(ProductNotFoundException.class, () -> this.productServiceImp.deleteProduct(null));
      }
}