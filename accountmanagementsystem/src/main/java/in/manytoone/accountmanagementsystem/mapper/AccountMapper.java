package in.manytoone.accountmanagementsystem.mapper;


import in.manytoone.accountmanagementsystem.dto.request.CreateAccountRequest;
import in.manytoone.accountmanagementsystem.dto.response.AccountResponse;
import in.manytoone.accountmanagementsystem.entity.AccountEntity;
import in.manytoone.accountmanagementsystem.entity.BranchEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses={})
public interface AccountMapper {


     AccountEntity toEntity(CreateAccountRequest createAccountRequest);


        @Mapping(source="accountEntity.id",target="id")
        @Mapping(source="accountEntity.accountNumber",target = "accountNumber")
        @Mapping(source="accountEntity.fullName",target = "fullName")
        @Mapping(source="accountEntity.balance",target = "balance")
        @Mapping(source="accountEntity.status",target = "status")
        @Mapping(source ="accountEntity.createdOn",target="createdOn")
         @Mapping(source="accountEntity.updatedOn",target = "updatedOn")
        @Mapping(source="branch.code",target = "branchCode")
        @Mapping(source="branch.name",target = "branchName")

       AccountResponse from(AccountEntity accountEntity, BranchEntity branch);

}
