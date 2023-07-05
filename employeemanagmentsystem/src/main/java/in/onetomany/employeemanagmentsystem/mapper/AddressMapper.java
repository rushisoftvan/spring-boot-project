package in.onetomany.employeemanagmentsystem.mapper;

import in.onetomany.employeemanagmentsystem.dto.request.CreateAddressRequest;
import in.onetomany.employeemanagmentsystem.dto.request.UpdateAddressRequest;
import in.onetomany.employeemanagmentsystem.dto.request.UpdateEmployeeRequest;
import in.onetomany.employeemanagmentsystem.dto.response.AddressResponse;
import in.onetomany.employeemanagmentsystem.entity.AddressEntity;
import in.onetomany.employeemanagmentsystem.entity.EmployeeEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {


     AddressEntity toEntity(CreateAddressRequest createAddressRequest);
     List<AddressEntity> toEntities(List<CreateAddressRequest> createAddressRequestList);

     List<AddressEntity> listoftoEntity(List<UpdateEmployeeRequest> dto);

     List<AddressResponse> toDtos(List<AddressEntity> entities);

     @Mapping(source="addressId",target = "id")
     AddressEntity toEntityForUpdate(UpdateAddressRequest dto);

     @Mapping(source = "id",target="addressId")
     AddressResponse toDto(AddressEntity address);

}
