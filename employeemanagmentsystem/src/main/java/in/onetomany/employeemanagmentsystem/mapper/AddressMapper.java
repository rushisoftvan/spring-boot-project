package in.onetomany.employeemanagmentsystem.mapper;

import in.onetomany.employeemanagmentsystem.dto.request.CreateAddressRequest;
import in.onetomany.employeemanagmentsystem.dto.response.AddressResponse;
import in.onetomany.employeemanagmentsystem.entity.AddressEntity;
import in.onetomany.employeemanagmentsystem.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface AddressMapper {


     public AddressEntity toEntity(CreateAddressRequest createAddressRequest);
     public  List<AddressEntity> toEntities(List<CreateAddressRequest> createAddressRequestList);


     @Mapping(source = "id",target="addressId")
     public AddressResponse toDto(AddressEntity address);

}
