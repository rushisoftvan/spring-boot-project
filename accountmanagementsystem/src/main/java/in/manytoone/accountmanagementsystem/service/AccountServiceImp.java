package in.manytoone.accountmanagementsystem.service;

import in.manytoone.accountmanagementsystem.Exception.RecordNotFoundException;
import in.manytoone.accountmanagementsystem.Repository.AccountRepository;
import in.manytoone.accountmanagementsystem.dto.request.CreateAccountRequest;
import in.manytoone.accountmanagementsystem.dto.request.UpdateAccountRequest;
import in.manytoone.accountmanagementsystem.dto.response.AccountResponse;
import in.manytoone.accountmanagementsystem.entity.AccountEntity;
import in.manytoone.accountmanagementsystem.entity.BranchEntity;
import in.manytoone.accountmanagementsystem.enums.Status;
import in.manytoone.accountmanagementsystem.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImp  implements AccountService{

    private final AccountMapper accountMapper;
    private  final AccountRepository accountRepository;
    private final BranchService branchService;

    @Override
    public AccountResponse creatAccount(CreateAccountRequest createAccountRequest) {
        BranchEntity branch = this.branchService.getBranchById(createAccountRequest.getBranchId());
        AccountEntity accountEntity = this.accountMapper.toEntity(createAccountRequest);
        accountEntity.setBranch(branch);
        AccountEntity savedAccount = this.accountRepository.save(accountEntity);
        AccountResponse account = this.accountMapper.from(savedAccount, savedAccount.getBranch());
        return account;
    }

    @Override
    @Transactional(readOnly = true)
    public AccountResponse getAccountById(Integer id) {
        AccountEntity accountEntity = getAccountEntity(id);
         return this.accountMapper.from(accountEntity, accountEntity.getBranch());
    }

    private AccountEntity getAccountEntity(Integer id) {
        Optional<AccountEntity> optionlAccount = this.accountRepository.findById(id);
        if(optionlAccount.isPresent()){
            return optionlAccount.get();

        }
        throw new RecordNotFoundException("Account is not available for id:"+ id);
    }

    @Override
    public void deleteAccountById(Integer id) {
        AccountEntity accountEntity = this.getAccountEntity(id);
        accountEntity.setStatus(Status.IN_ACTIVE);
        AccountEntity deletedEntity = this.accountRepository.save(accountEntity);
        AccountEntity deletedAccount = this.accountRepository.save(deletedEntity);
    }

    @Transactional
    @Override
    public AccountResponse updateAccount(Integer id, UpdateAccountRequest updateAccountRequest) {
        AccountEntity accountEntity = getAccountEntity(id);
        BranchEntity branchforupdate = this.branchService.getBranchById(updateAccountRequest.getBranchId());
        accountEntity.setAccountNumber(updateAccountRequest.getAccountNumber());
        accountEntity.setFullName(updateAccountRequest.getFullName());
        accountEntity.setBalance(updateAccountRequest.getBalance());
        accountEntity.setBranch(branchforupdate);
        AccountEntity updatedAcoount = this.accountRepository.save(accountEntity);
        return this.accountMapper.from(updatedAcoount, updatedAcoount.getBranch());
    }
}
