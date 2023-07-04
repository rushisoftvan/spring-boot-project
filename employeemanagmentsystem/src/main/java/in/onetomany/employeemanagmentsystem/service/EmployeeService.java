package in.onetomany.employeemanagmentsystem.service;

import in.onetomany.employeemanagmentsystem.Exception.RecordNotFountException;
import in.onetomany.employeemanagmentsystem.StatusEnum.StatusEnum;
import in.onetomany.employeemanagmentsystem.dto.request.CreateEmployeeRequest;
import in.onetomany.employeemanagmentsystem.dto.response.EmployeeResponse;
import in.onetomany.employeemanagmentsystem.entity.AddressEntity;
import in.onetomany.employeemanagmentsystem.entity.EmployeeEntity;
import in.onetomany.employeemanagmentsystem.mapper.EmployeeMapper;
import in.onetomany.employeemanagmentsystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final AddressRepository addressRepository;

    public EmployeeResponse addEmployee(CreateEmployeeRequest createEmployeeRequest) {
        System.out.println("createEmployeeRequest = " + createEmployeeRequest.toString());
        log.debug("<<<<<<<<< addEmployee()");

        EmployeeEntity employeeEntity = this.employeeMapper.toEntity(createEmployeeRequest);

        System.out.println("employeeEntity.getAddressEntityList() = " + employeeEntity.getAddressEntityList());
        System.out.println("employeeEntity.getName() = " + employeeEntity.getName());
        EmployeeEntity savedEntity = this.employeeRepository.save(employeeEntity);
        List<AddressEntity> addressEntityList = employeeEntity.getAddressEntityList();
       this.addressRepository.saveAll(addressEntityList);
        log.debug("addEmployee >>>>>>>");
        return this.employeeMapper.toDto(savedEntity);
    }

    @Transactional
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

    @Transactional
    public void deleteById(Integer id)
    {
        EmployeeEntity employeEntity = this.getEmployeEntity(id);
        employeEntity.setStatus(StatusEnum.IN_ACTIVE);
        this.employeeRepository.save(employeEntity);

    }

}
