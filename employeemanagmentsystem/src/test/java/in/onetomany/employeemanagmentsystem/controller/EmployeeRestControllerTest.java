package in.onetomany.employeemanagmentsystem.controller;

import in.onetomany.employeemanagmentsystem.StatusEnum.StatusEnum;
import in.onetomany.employeemanagmentsystem.dto.request.CreateAddressRequest;
import in.onetomany.employeemanagmentsystem.dto.request.CreateEmployeeRequest;
import in.onetomany.employeemanagmentsystem.dto.request.EmployeePagedListRequest;
import in.onetomany.employeemanagmentsystem.dto.response.AddressResponse;
import in.onetomany.employeemanagmentsystem.dto.response.ApiResponse;
import in.onetomany.employeemanagmentsystem.dto.response.EmployeePageListResponse;
import in.onetomany.employeemanagmentsystem.dto.response.EmployeeResponse;
import in.onetomany.employeemanagmentsystem.entity.AddressEntity;
import in.onetomany.employeemanagmentsystem.entity.EmployeeEntity;
import in.onetomany.employeemanagmentsystem.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class EmployeeRestControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRestController  EmployeeRestController;


    @Test
     public void testsaveEmployee(){
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeId(1);
        employeeResponse.setEmployeeName("rushikesh");
        employeeResponse.setEmployeeAge(45);
        employeeResponse.setEmployeeStatus(StatusEnum.ACTIVE);

        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setHouseNo(45);
        addressResponse.setCity("pune");
        employeeResponse.setAddresses(Arrays.asList(addressResponse));

        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
        List<CreateAddressRequest> address = new ArrayList<>();
        CreateAddressRequest createAddressRequest = new CreateAddressRequest();
        createAddressRequest.setHouseNo(45);
        createAddressRequest.setCity("pune");
        createEmployeeRequest.setEmployeeName("rushikesh");
        createEmployeeRequest.setEmployeeAge(45);
        address.add(createAddressRequest);
        createEmployeeRequest.setAddresses(address);

        Mockito.when(this.employeeService.addEmployee(createEmployeeRequest)).thenReturn(employeeResponse);
        ApiResponse<EmployeeResponse> apiResponse = this.EmployeeRestController.saveEmployee(createEmployeeRequest);
        assertEquals(employeeResponse,apiResponse.getData());
        assertEquals(201, apiResponse.getStatusCode());
    }

    @Test
    public void testsaveEmployeeEmployeNameNull(){

        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setHouseNo(45);
        addressResponse.setCity("pune");


        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
        List<CreateAddressRequest> address = new ArrayList<>();
        CreateAddressRequest createAddressRequest = new CreateAddressRequest();
        createAddressRequest.setHouseNo(45);
        createAddressRequest.setCity("pune");
        createEmployeeRequest.setEmployeeName(null);
        createEmployeeRequest.setEmployeeAge(45);
        address.add(createAddressRequest);
        createEmployeeRequest.setAddresses(address);
        assertThrows(ConstraintViolationException.class,()->this.EmployeeRestController.saveEmployee(createEmployeeRequest));
    }

    @Test
    public void fetchEmployeeById(){
        Integer id=1;
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeId(1);
        employeeResponse.setEmployeeName("rushikesh");
        employeeResponse.setEmployeeAge(45);
        employeeResponse.setEmployeeStatus(StatusEnum.ACTIVE);

        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setHouseNo(45);
        addressResponse.setCity("pune");
        employeeResponse.setAddresses(Arrays.asList(addressResponse));
         Mockito.when(this.employeeService.getEmployeeById(id)).thenReturn(employeeResponse);
        ApiResponse<EmployeeResponse> employeeResponseApiResponse = this.EmployeeRestController.fetchEmployeeById(id);
        assertEquals(employeeResponse,employeeResponseApiResponse.getData());
    }

    @Test
     public void  fetchEmployeeByIfIdNull(){
       assertThrows(ConstraintViolationException.class,()->this.EmployeeRestController.fetchEmployeeById(null));
     }

     @Test
     public void fetchEmployeeByIfIdZero(){
         Integer id=0;
         assertThrows(ConstraintViolationException.class,()->this.EmployeeRestController.fetchEmployeeById(id));
     }

     @Test
     public void deleteEmployeeById()
     {
         Integer id =1;
         //Mockito.doNothing().when(this.EmployeeRestController.deleteEmployeeById(1));
         Mockito.doNothing().when(this.employeeService).deleteEmployeeById(1);
         ApiResponse<Boolean> booleanApiResponse = this.EmployeeRestController.deleteEmployeeById(id);
         Mockito.verify(employeeService, times(1)).deleteEmployeeById(id);
     }

     @Test
    public void  getAllEmployee()
    {
        EmployeePagedListRequest employeePagedListRequest = new EmployeePagedListRequest();
        employeePagedListRequest.setPageSize(2);
        employeePagedListRequest.setPageNo(1);

        List<EmployeeResponse> employess = new ArrayList<>();
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeId(1);
        employeeResponse.setEmployeeName("rushikesh");
        employeeResponse.setEmployeeAge(45);
        employeeResponse.setEmployeeStatus(StatusEnum.ACTIVE);

        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setHouseNo(45);
        addressResponse.setCity("pune");
        employeeResponse.setAddresses(Arrays.asList(addressResponse));
        employess.add(employeeResponse);
        EmployeePageListResponse employeePageListResponse = new EmployeePageListResponse();
        employeePageListResponse.setEmployeeResponseList(employess);
        Mockito.when(this.employeeService.getAllEmployee(employeePagedListRequest)).thenReturn(employeePageListResponse);
        ApiResponse<EmployeePageListResponse> allEmployee = this.EmployeeRestController.getAllEmployee(employeePagedListRequest);
        assertEquals(200,allEmployee.getStatusCode());
         assertEquals(employeePageListResponse,allEmployee.getData());
    }
}