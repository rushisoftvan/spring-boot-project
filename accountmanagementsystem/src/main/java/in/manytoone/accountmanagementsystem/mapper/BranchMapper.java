package in.manytoone.accountmanagementsystem.mapper;


import in.manytoone.accountmanagementsystem.dto.request.CreateBranchRequest;
import in.manytoone.accountmanagementsystem.dto.response.BranchResponse;
import in.manytoone.accountmanagementsystem.entity.BranchEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,injectionStrategy = InjectionStrategy.CONSTRUCTOR )
public interface BranchMapper {

    @Mapping(source = "branchCode",target = "code")
    @Mapping(source = "branchName",target = "name")
    BranchEntity toEntity(CreateBranchRequest createBranchRequest);

    @Mapping(source = "name",target = "branchName")
    @Mapping(source ="code" ,target = "branchCode")
    BranchResponse toDto(BranchEntity branchEntity);

    List<BranchResponse> toBranchList(List<BranchEntity> branches);

}
