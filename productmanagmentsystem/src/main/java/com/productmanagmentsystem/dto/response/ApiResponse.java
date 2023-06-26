package com.productmanagmentsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private Object data;

    private Integer statusCode;

    private List<String> errors;

    private String url;



    public ApiResponse(Object data, Integer statusCode){
        this.data=data;
        this.statusCode=statusCode;
    }

    public ApiResponse(List<String> errors,Integer statusCode,String url){
        this.statusCode=statusCode;
        this.errors=errors;
        this.url=url;
    }
}
