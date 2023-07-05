package in.onetomany.employeemanagmentsystem.service;

import com.sun.source.tree.ModuleTree;
import in.onetomany.employeemanagmentsystem.Exception.CustomException;
import in.onetomany.employeemanagmentsystem.Exception.RecordNotFountException;
import in.onetomany.employeemanagmentsystem.StatusEnum.StatusEnum;
import in.onetomany.employeemanagmentsystem.dto.request.CreateAddressRequest;
import in.onetomany.employeemanagmentsystem.dto.request.CreateEmployeeRequest;
import in.onetomany.employeemanagmentsystem.dto.request.EmployeePagedListRequest;
import in.onetomany.employeemanagmentsystem.dto.response.AddressResponse;
import in.onetomany.employeemanagmentsystem.dto.response.EmployeePageListResponse;
import in.onetomany.employeemanagmentsystem.dto.response.EmployeeResponse;
import in.onetomany.employeemanagmentsystem.entity.AddressEntity;
import in.onetomany.employeemanagmentsystem.entity.EmployeeEntity;
import in.onetomany.employeemanagmentsystem.mapper.AddressMapper;
import in.onetomany.employeemanagmentsystem.mapper.AddressMapperImpl;
import in.onetomany.employeemanagmentsystem.mapper.EmployeeMapper;
import in.onetomany.employeemanagmentsystem.mapper.EmployeeMapperImpl;
import in.onetomany.employeemanagmentsystem.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class EmployeeServiceTest {


    @Test
    public void testaddEmployee() {
        AddressMapper addressMapper = new AddressMapperImpl();
        EmployeeMapper employeeMapper = new EmployeeMapperImpl(addressMapper);
        EmployeeRepository employeeRepositorymock = Mockito.mock(EmployeeRepository.class);
        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
        List<CreateAddressRequest> address = new ArrayList<>();
        CreateAddressRequest createAddressRequest = new CreateAddressRequest();
        createAddressRequest.setHouseNo(12);
        createAddressRequest.setCity("ahmedabad");
        createEmployeeRequest.setEmployeeName("rushikesh");
        address.add(createAddressRequest);
        createEmployeeRequest.setAddresses(address);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        AddressEntity address1 = new AddressEntity();
        employeeEntity.setId(1);
        employeeEntity.setName("rushikesh");
        List<AddressEntity> listOfAddressEntity = new ArrayList<>();
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(1);
        addressEntity.setHouseNo(12);
        addressEntity.setCity("ahmedabad");
        listOfAddressEntity.add(addressEntity);

        Mockito.when(employeeRepositorymock.save(Mockito.any(EmployeeEntity.class))).thenReturn(employeeEntity);
        EmployeeService employeeService = new EmployeeService(employeeRepositorymock, employeeMapper);
        EmployeeResponse employeeResponse = employeeService.addEmployee(createEmployeeRequest);

        assertEquals("rushikesh", employeeResponse.getEmployeeName());
    }

    @Test
    public void testaddEmployeeifCreateEmployeeRequestNull() {
        AddressMapper addressMapper = new AddressMapperImpl();
        EmployeeMapper employeeMapper = new EmployeeMapperImpl(addressMapper);
        EmployeeRepository employeeRepositorymock = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepositorymock, employeeMapper);
        CustomException customException = assertThrows(CustomException.class, () -> employeeService.addEmployee(null));
        assertEquals("CreateEmployeeRequest should not be null", customException.getMessage());
    }

    @Test
    public void testGetEmployeeById() {
        Integer id = 1;

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeId(1);
        employeeResponse.setEmployeeAge(45);
        employeeResponse.setEmployeeStatus(StatusEnum.ACTIVE);
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setAddressId(1);
        addressResponse.setCity("pune");
        addressResponse.setHouseNo(12);


        EmployeeEntity employeeEntity = new EmployeeEntity();
        AddressEntity address1 = new AddressEntity();
        employeeEntity.setId(1);
        employeeEntity.setName("rushikesh");
        List<AddressEntity> listOfAddressEntity = new ArrayList<>();
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(1);
        addressEntity.setHouseNo(12);
        addressEntity.setCity("ahmedabad");
        listOfAddressEntity.add(addressEntity);
        AddressMapper a = new AddressMapperImpl();
        EmployeeMapper employeeMapper = new EmployeeMapperImpl(a);
        EmployeeRepository employeeRepositorymock = Mockito.mock(EmployeeRepository.class);
        Mockito.when(employeeRepositorymock.findById(id)).thenReturn(Optional.of(employeeEntity));
        EmployeeService employeeService = new EmployeeService(employeeRepositorymock, employeeMapper);
        EmployeeResponse employee = employeeService.getEmployeeById(id);
        assertEquals("rushikesh", employee.getEmployeeName());
    }

    @Test
    public void testGetEmployeeByIdIfDataIsNotAvailableForId() {
        Integer id = 50;
        AddressMapper addressMapper = new AddressMapperImpl();
        EmployeeMapper employeeMapper = new EmployeeMapperImpl(addressMapper);
        EmployeeRepository employeeRepositorymock = Mockito.mock(EmployeeRepository.class);
        Mockito.when(employeeRepositorymock.findById(id)).thenReturn(Optional.empty());
        EmployeeService employeeService = new EmployeeService(employeeRepositorymock, employeeMapper);
        RecordNotFountException recordNotFountException = assertThrows(RecordNotFountException.class, () -> employeeService.getEmployeeById(id));
        assertEquals("Employee is not available :" + id, recordNotFountException.getMessage());
    }

    @Test
    public void testdeleteById() {
        Integer id = 1;

        EmployeeEntity employeeEntity = new EmployeeEntity();
        AddressEntity address1 = new AddressEntity();
        employeeEntity.setId(1);
        employeeEntity.setName("rushikesh");
        List<AddressEntity> listOfAddressEntity = new ArrayList<>();
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(1);
        addressEntity.setHouseNo(12);
        addressEntity.setCity("ahmedabad");
        listOfAddressEntity.add(addressEntity);


        AddressMapper addressMapper = new AddressMapperImpl();
        EmployeeMapper employeeMapper = new EmployeeMapperImpl(addressMapper);
        EmployeeRepository employeeRepositorymock = Mockito.mock(EmployeeRepository.class);
        Mockito.when(employeeRepositorymock.findById(id)).thenReturn(Optional.of(employeeEntity));
        employeeEntity.setStatus(StatusEnum.IN_ACTIVE);
        Mockito.when(employeeRepositorymock.save(employeeEntity)).thenReturn(employeeEntity);
        EmployeeService employeeService = new EmployeeService(employeeRepositorymock, employeeMapper);
        employeeService.deleteEmployeeById(id);
    }

    @Test
    public void testGetAllEmployee() {
        EmployeePagedListRequest employeePagedListRequest = new EmployeePagedListRequest();
        employeePagedListRequest.setPageNo(1);
        employeePagedListRequest.setPageSize(2);
           List<EmployeeEntity> employess = new ArrayList<>();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        AddressEntity address1 = new AddressEntity();
        employeeEntity.setId(1);
        employeeEntity.setName("rushikesh");
        List<AddressEntity> listOfAddressEntity = new ArrayList<>();
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(1);
        addressEntity.setHouseNo(12);
        addressEntity.setCity("ahmedabad");
        listOfAddressEntity.add(addressEntity);

        employess.add(employeeEntity);

        AddressMapper addressMapper = new AddressMapperImpl();
        EmployeeMapper employeeMapper = new EmployeeMapperImpl(addressMapper);
        EmployeeRepository employeeRepositorymock = Mockito.mock(EmployeeRepository.class);

        Integer pageSize =  employeePagedListRequest.getPageSize();
        Integer pageNumber= employeePagedListRequest.getPageNo()-1;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        // Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<EmployeeEntity> page=new PageImpl(employess,pageable,1);
        Mockito.when(employeeRepositorymock.findAll(pageable)).thenReturn(page);
        EmployeeService employeeService = new EmployeeService(employeeRepositorymock, employeeMapper);
        EmployeePageListResponse allEmployee = employeeService.getAllEmployee(employeePagedListRequest);
        assertEquals(1,allEmployee.getEmployeeResponseList().size());
    }

    @Test
       public void testGetAllEmployeeIfEmployeePagedListRequest()
       {
           AddressMapper addressMapper = new AddressMapperImpl();
           EmployeeMapper employeeMapper = new EmployeeMapperImpl(addressMapper);
           EmployeeRepository employeeRepositorymock = Mockito.mock(EmployeeRepository.class);
           EmployeeService employeeService = new EmployeeService(employeeRepositorymock, employeeMapper);
           assertThrows(CustomException.class,()->employeeService.getAllEmployee(null));
       }
}


