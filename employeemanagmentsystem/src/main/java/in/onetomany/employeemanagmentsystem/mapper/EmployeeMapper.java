package in.onetomany.employeemanagmentsystem.mapper;

import in.onetomany.employeemanagmentsystem.dto.request.CreateAddressRequest;
import in.onetomany.employeemanagmentsystem.dto.request.CreateEmployeeRequest;
import in.onetomany.employeemanagmentsystem.dto.response.EmployeeResponse;
import in.onetomany.employeemanagmentsystem.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {

    @Mapping(source = "employeeName",target = "name")
    @Mapping(source="employeeAge",target = "age")
    @Mapping(source="addresses",target="addressEntityList")
    public EmployeeEntity toEntity(CreateEmployeeRequest createEmployeeRequest);

    @Mapping(source = "id",target = "employeeId")
    @Mapping(source = "name",target="employeeName")
    @Mapping(source="age",target="employeeAge")
    @Mapping(source = "status",target="employeeStatus")
    @Mapping(source="addressEntityList",target="addresses")
    public EmployeeResponse toDto(EmployeeEntity employe);

}
