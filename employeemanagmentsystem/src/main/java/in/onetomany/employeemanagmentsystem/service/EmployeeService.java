package in.onetomany.employeemanagmentsystem.service;

import in.onetomany.employeemanagmentsystem.Exception.CustomException;
import in.onetomany.employeemanagmentsystem.Exception.RecordNotFountException;
import in.onetomany.employeemanagmentsystem.StatusEnum.StatusEnum;
import in.onetomany.employeemanagmentsystem.dto.request.CreateEmployeeRequest;
import in.onetomany.employeemanagmentsystem.dto.request.EmployeePagedListRequest;
import in.onetomany.employeemanagmentsystem.dto.request.UpdateEmployeeRequest;
import in.onetomany.employeemanagmentsystem.dto.response.EmployeePageListResponse;
import in.onetomany.employeemanagmentsystem.dto.response.EmployeeResponse;
import in.onetomany.employeemanagmentsystem.entity.EmployeeEntity;
import in.onetomany.employeemanagmentsystem.mapper.EmployeeMapper;
import in.onetomany.employeemanagmentsystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;


    public EmployeeResponse addEmployee(CreateEmployeeRequest createEmployeeRequest) {
        log.debug("<<<<<<<<< addEmployee()");
        checkCreateEmployeeRequest(createEmployeeRequest);
        EmployeeEntity employeeEntity = this.employeeMapper.toEntity(createEmployeeRequest);

        log.info("Converted employee entity : {}" , employeeEntity);

        System.out.println("employeeEntity.getAddressEntityList() = " + employeeEntity.getAddressEntityList());
        System.out.println("employeeEntity.getName() = " + employeeEntity.getName());
        EmployeeEntity savedEntity = this.employeeRepository.save(employeeEntity);
        log.debug("addEmployee >>>>>>>");
        return this.employeeMapper.toDto(savedEntity);
    }

    private static void checkCreateEmployeeRequest(CreateEmployeeRequest createEmployeeRequest) {
        if(createEmployeeRequest ==null)
        {
            throw new CustomException("CreateEmployeeRequest should not be null");
        }
    }

    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(Integer id)
    {
        EmployeeEntity employeEntity = getEmployeEntity(id);
        return  this.employeeMapper.toDto(employeEntity);
    }

    private EmployeeEntity getEmployeEntity(Integer id) {
        Optional<EmployeeEntity> employee = this.employeeRepository.findById(id);
        if(employee.isPresent()){
            EmployeeEntity employeeEntity = employee.get();
            employeeEntity.getAddressEntityList();
            return employeeEntity;
        }
        else{
            throw new RecordNotFountException("Employee is not available :"+ id);
        }
    }


    public void deleteEmployeeById(Integer id)
    {
        log.debug("<<<<<<<<< addEmployee()");
        EmployeeEntity employeEntity = this.getEmployeEntity(id);
        employeEntity.setStatus(StatusEnum.IN_ACTIVE);
        this.employeeRepository.save(employeEntity);

    }


    public void updateEmployee(Integer id, UpdateEmployeeRequest employee){
        EmployeeEntity employeEntity = getEmployeEntity(id);

    }


    @Transactional(readOnly = true)
    public EmployeePageListResponse getAllEmployee(EmployeePagedListRequest employeePagedListRequest){
        if(employeePagedListRequest==null){
            throw new  CustomException("employeePagedListRequest should not be null");
        }
        Integer pageNo = employeePagedListRequest.getPageNo()-1;
        Integer pageSize = employeePagedListRequest.getPageSize();
        Pageable page = PageRequest.of(pageNo, pageSize);
        Page<EmployeeEntity> employePage = this.employeeRepository.findAll(page);
        List<EmployeeResponse> listOfEmployeeResponse = this.employeeMapper.toListOfEmployeeResponse(employePage.getContent());
        EmployeePageListResponse employeePageListResponse = new EmployeePageListResponse();
        employeePageListResponse.setEmployeeResponseList(listOfEmployeeResponse);
        employeePageListResponse.setTotalRecords(employePage.getTotalElements());
        return employeePageListResponse;
    }

}
