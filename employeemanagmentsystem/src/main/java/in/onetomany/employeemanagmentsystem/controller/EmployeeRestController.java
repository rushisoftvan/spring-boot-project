package in.onetomany.employeemanagmentsystem.controller;

import in.onetomany.employeemanagmentsystem.dto.request.CreateEmployeeRequest;
import in.onetomany.employeemanagmentsystem.dto.response.ApiResponse;
import in.onetomany.employeemanagmentsystem.dto.response.EmployeeResponse;
import in.onetomany.employeemanagmentsystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @PostMapping("/employees")
    public ApiResponse<Object> saveEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest){
        log.debug("<<<<<<<<< saveEmployee()");
        EmployeeResponse employeeResponse = this.employeeService.addEmployee(createEmployeeRequest);
        log.debug("saveEmployee() >>>>>>>");
        return ApiResponse.builder().data(employeeResponse).statusCode(HttpStatus.CREATED.value()).build();
    }


    @GetMapping("employees/{id}")
    public ApiResponse.ApiResponseBuilder<Object> fetchEmployeeById(@PathVariable("id") Integer id){
        log.debug("<<<<<<<<< fetchEmployeeById()");
        EmployeeResponse employee = this.employeeService.getEmployeeById(id);
        log.debug("fetchEmployeeById() >>>>>>>");

        return ApiResponse.builder().data(employee).statusCode(HttpStatus.OK.value());
    }

    @DeleteMapping("employees/{id}")
    public ApiResponse.ApiResponseBuilder<Object> deleteEmployeeById(@PathVariable("id") Integer id){
        log.debug("<<<<<<<<< deleteEmployeeById()");
       this.employeeService.deleteById(id);
        log.debug("deleteEmployeeById() >>>>>>>");
       return ApiResponse.builder().data(true).statusCode(HttpStatus.OK.value());
    }
}
