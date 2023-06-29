package com.productmanagmentsystem.controller;

import com.productmanagmentsystem.dto.request.CreateProductRequest;
import com.productmanagmentsystem.dto.request.UpdateProductRequest;
import com.productmanagmentsystem.dto.response.ApiResponse;
import com.productmanagmentsystem.dto.response.ProductResponse;
import com.productmanagmentsystem.enums.StatusEnum;
import com.productmanagmentsystem.service.ProductServiceImp;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
class ProductRestControllerTest {
    @MockBean
    private ProductServiceImp productServiceImp;

    @Autowired
    private ProductRestController productRestController;


    @Test
    public void testgetPagedProductList() {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductName("Table");
        productResponse.setProductPrice(500);
        productResponse.setStatus(StatusEnum.ACTIVE);
        List<ProductResponse> productList = Arrays.asList(productResponse);
        ApiResponse apiResponse = new ApiResponse(productResponse, HttpStatus.OK.value());
        Mockito.when(this.productServiceImp.getPagedProductList()).thenReturn(productList);
        ApiResponse pagedProductList = this.productRestController.getPagedProductList();
        assertEquals(productList, pagedProductList.getData());
    }

    @Test
    public void getProductById() {
        Integer id = 1;
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductName("Table");
        productResponse.setProductPrice(500);
        Mockito.when(this.productServiceImp.fetchProductById(id)).thenReturn(productResponse);
        ApiResponse productById = this.productRestController.getProductById(id);
        assertEquals(productResponse, productById.getData());
    }

    @Test
    public void getProductByIdIdZero() {
        Integer id = 0;
        assertThrows(ConstraintViolationException.class, () -> this.productRestController.getProductById(id));
    }

    @Test
    public void deleteProductById() {
        Integer id = 1;
        String expected = "data is deleted for id : " + id;
        Mockito.when(this.productServiceImp.deleteProduct(1)).thenReturn("data is deleted for id : " + id);
        ApiResponse apiResponse = this.productRestController.deleteProductById(id);
        assertEquals(expected, apiResponse.getData());
    }

    @Test
    public void testSaveProduct() {
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setProductName("Mobile");
        createProductRequest.setProductPrice(5000);
        createProductRequest.setProductDescription("good product");

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductId(1);
        productResponse.setProductName(createProductRequest.getProductName());
        productResponse.setProductDescription(createProductRequest.getProductDescription());
        productResponse.setProductPrice(productResponse.getProductPrice());

        Mockito.when(this.productServiceImp.saveProduct(createProductRequest)).thenReturn(productResponse);
        ApiResponse apiResponse = this.productRestController.saveProduct(createProductRequest);
        assertEquals(productResponse,apiResponse.getData());
    }

    @Test
    public void testSaveProductCreateProductRequestNull(){
     Mockito.when(this.productServiceImp.saveProduct(null)).thenThrow(NullPointerException.class);
     assertThrows(NullPointerException.class,()->this.productRestController.saveProduct(null));
    }

    @Test
    public void testSaveProductCreateProductRequestNameNull(){
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setProductName("table");
        createProductRequest.setProductDescription(null);
        assertThrows(ConstraintViolationException.class,()->this.productRestController.saveProduct(createProductRequest));
    }

    @Test
    public void testUpdateProduct(){
        Integer id=1;

        UpdateProductRequest updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setProductName("Table");
        updateProductRequest.setProductDescription("desc");
        updateProductRequest.setProductPrice(200);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductId(1);
        productResponse.setProductName("Table");
        productResponse.setProductPrice(200);
        Mockito.when(this.productServiceImp.updateProduct(1,updateProductRequest)).thenReturn(productResponse);
       ProductResponse updatedproduct = this.productServiceImp.updateProduct(1,updateProductRequest);
       assertEquals("Table",updatedproduct.getProductName());
    }
}





