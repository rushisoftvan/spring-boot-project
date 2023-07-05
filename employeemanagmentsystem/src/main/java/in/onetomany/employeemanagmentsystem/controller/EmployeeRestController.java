package in.onetomany.employeemanagmentsystem.controller;

import in.onetomany.employeemanagmentsystem.dto.request.CreateEmployeeRequest;
import in.onetomany.employeemanagmentsystem.dto.request.EmployeePagedListRequest;
import in.onetomany.employeemanagmentsystem.dto.response.ApiResponse;
import in.onetomany.employeemanagmentsystem.dto.response.EmployeePageListResponse;
import in.onetomany.employeemanagmentsystem.dto.response.EmployeeResponse;
import in.onetomany.employeemanagmentsystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
@Validated
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @PostMapping("/employees")
    public ApiResponse<EmployeeResponse> saveEmployee(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest){
        log.debug("<<<<<<<<< saveEmployee()");
        EmployeeResponse employeeResponse = this.employeeService.addEmployee(createEmployeeRequest);
        log.debug("saveEmployee() >>>>>>>");
        ApiResponse.ApiResponseBuilder<EmployeeResponse> builder = ApiResponse.builder();
        return builder.data(employeeResponse).statusCode(HttpStatus.CREATED.value()).build();
    }


    @GetMapping("employees/{id}")
    public ApiResponse<EmployeeResponse> fetchEmployeeById(@PathVariable("id") @Positive(message = "Id should be greater then zero") @NotNull(message = "Id should not be null") Integer id){
        log.debug("<<<<<<<<< fetchEmployeeById()");
        EmployeeResponse employee = this.employeeService.getEmployeeById(id);
        log.debug("fetchEmployeeById() >>>>>>>");

        ApiResponse.ApiResponseBuilder<EmployeeResponse> builder = ApiResponse.builder();
        return builder.data(employee).statusCode(HttpStatus.OK.value()).build();
    }

    @DeleteMapping("employees/{id}")
    public ApiResponse<Boolean> deleteEmployeeById(@PathVariable("id") @Positive(message = "Id should be greater then zero") @NotNull(message = "Id should not be null")  Integer id){
        log.debug("<<<<<<<<< deleteEmployeeById()");
       this.employeeService.deleteEmployeeById(id);
        log.debug("deleteEmployeeById() >>>>>>>");
        ApiResponse.ApiResponseBuilder<Boolean> builder = ApiResponse.builder();
        return builder.data(true).statusCode(HttpStatus.OK.value()).build();
    }

    @PostMapping("/pageemployees")
    public ApiResponse<EmployeePageListResponse> getAllEmployee(@RequestBody EmployeePagedListRequest pageRequest){
        log.debug("<<<<<<<<< getAllEmployee()");
        EmployeePageListResponse allEmployee = this.employeeService.getAllEmployee(pageRequest);
        log.debug("getAllEmployee() >>>>>>>");
        ApiResponse.ApiResponseBuilder<EmployeePageListResponse> builder = ApiResponse.builder();
        return  builder.data(allEmployee).statusCode(HttpStatus.OK.value()).build();
    }



}
