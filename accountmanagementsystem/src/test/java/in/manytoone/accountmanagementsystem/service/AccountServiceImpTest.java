package in.manytoone.accountmanagementsystem.service;

import in.manytoone.accountmanagementsystem.Exception.CustomException;
import in.manytoone.accountmanagementsystem.Exception.RecordNotFoundException;
import in.manytoone.accountmanagementsystem.Repository.AccountRepository;
import in.manytoone.accountmanagementsystem.dto.request.CreateAccountRequest;
import in.manytoone.accountmanagementsystem.dto.request.CreateBranchRequest;
import in.manytoone.accountmanagementsystem.dto.request.UpdateAccountRequest;
import in.manytoone.accountmanagementsystem.dto.response.AccountResponse;
import in.manytoone.accountmanagementsystem.entity.AccountEntity;
import in.manytoone.accountmanagementsystem.entity.BranchEntity;
import in.manytoone.accountmanagementsystem.enums.Status;
import in.manytoone.accountmanagementsystem.mapper.AccountMapper;
import in.manytoone.accountmanagementsystem.mapper.AccountMapperImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImpTest {

    @Test
    public void testCreateAccount() {

        AccountMapper accountMapper = new AccountMapperImpl();
        AccountRepository accountMock = Mockito.mock(AccountRepository.class);
        BranchServiceImp branchServiceMock = Mockito.mock(BranchServiceImp.class);

        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setAccountNumber("2345");
        createAccountRequest.setFullName("rushikesh");
        createAccountRequest.setBranchId(1);
        createAccountRequest.setBalance(BigDecimal.valueOf(34576.0000));

        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setName("odhav");
        branchEntity.setId(1);
        branchEntity.setCode("123456");

        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setBalance(BigDecimal.valueOf(34576.0000));
        accountEntity.setAccountNumber("2345");
        accountEntity.setBranch(branchEntity);
        accountEntity.setFullName("rushikesh");

        Mockito.when(branchServiceMock.getBranchById(createAccountRequest.getBranchId())).thenReturn(branchEntity);
        Mockito.when(accountMock.save(Mockito.any(AccountEntity.class))).thenReturn(accountEntity);
        Mockito.when(branchServiceMock.getBranchById(createAccountRequest.getBranchId())).thenReturn(branchEntity);
        AccountServiceImp accountServiceImp = new AccountServiceImp(accountMapper, accountMock, branchServiceMock);

        AccountResponse accountResponse = accountServiceImp.creatAccount(createAccountRequest);
        assertEquals("rushikesh", accountResponse.getFullName());
    }

    @Test
    public void testCreateAccountIfCreateAccountRequestNull() {

        AccountMapper accountMapper = new AccountMapperImpl();
        AccountRepository accountMock = Mockito.mock(AccountRepository.class);
        BranchServiceImp branchServiceMock = Mockito.mock(BranchServiceImp.class);
        AccountServiceImp accountServiceImp = new AccountServiceImp(accountMapper, accountMock, branchServiceMock);

        assertThrows(CustomException.class, () -> accountServiceImp.creatAccount(null));

    }

    public void getAccountById() {
        Integer id = 1;
        AccountMapper accountMapper = new AccountMapperImpl();
        AccountRepository accountMock = Mockito.mock(AccountRepository.class);
        BranchServiceImp branchServiceMock = Mockito.mock(BranchServiceImp.class);
        AccountServiceImp accountServiceImp = new AccountServiceImp(accountMapper, accountMock, branchServiceMock);

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1);
        accountEntity.setFullName("rushikesh");
        accountEntity.setStatus(Status.ACTIVE);
        accountEntity.setAccountNumber("345677");

        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setCode("34566");
        branchEntity.setName("odhav");
        branchEntity.setId(1);

        accountEntity.setBranch(branchEntity);

        Mockito.when(accountMock.findById(1)).thenReturn(Optional.of(accountEntity));
        AccountResponse account = accountServiceImp.getAccountById(1);
        assertEquals("rushikesh", account.getFullName());
    }

    @Test
    public void getAccountByIdIfIdNull() {
        Integer id = null;
        AccountMapper accountMapper = new AccountMapperImpl();
        AccountRepository accountMock = Mockito.mock(AccountRepository.class);
        BranchServiceImp branchServiceMock = Mockito.mock(BranchServiceImp.class);
        AccountServiceImp accountServiceImp = new AccountServiceImp(accountMapper, accountMock, branchServiceMock);
        assertThrows(CustomException.class, () -> accountServiceImp.getAccountById(id));
    }

    @Test
    public void getAccountByIdIfAccountIsNotAvailable() {
        Integer id = 50;
        AccountMapper accountMapper = new AccountMapperImpl();
        AccountRepository accountMock = Mockito.mock(AccountRepository.class);
        BranchServiceImp branchServiceMock = Mockito.mock(BranchServiceImp.class);
        AccountServiceImp accountServiceImp = new AccountServiceImp(accountMapper, accountMock, branchServiceMock);
        Mockito.when(accountMock.findById(id)).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> accountServiceImp.getAccountById(id));
    }

    @Test
    public void deleteById() {
        Integer id = 1;

        AccountMapper accountMapper = new AccountMapperImpl();
        AccountRepository accountMock = Mockito.mock(AccountRepository.class);
        BranchServiceImp branchServiceMock = Mockito.mock(BranchServiceImp.class);

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1);
        accountEntity.setFullName("rushikesh");
        accountEntity.setBalance(BigDecimal.valueOf(3444.789));
        accountEntity.setStatus(Status.ACTIVE);

        Mockito.when(accountMock.findById(1)).thenReturn(Optional.of(accountEntity));
        accountEntity.setStatus(Status.IN_ACTIVE);
        Mockito.when(accountMock.save(accountEntity)).thenReturn(accountEntity);
        AccountServiceImp accountServiceImp = new AccountServiceImp(accountMapper, accountMock, branchServiceMock);
        Boolean status = accountServiceImp.deleteAccountById(id);
        assertEquals(true, status);
    }

    @Test
    public void testDeleteByIdIfNull() {
        Integer id = null;

        AccountMapper accountMapper = new AccountMapperImpl();
        AccountRepository accountMock = Mockito.mock(AccountRepository.class);
        BranchServiceImp branchServiceMock = Mockito.mock(BranchServiceImp.class);
        AccountServiceImp accountServiceImp = new AccountServiceImp(accountMapper, accountMock, branchServiceMock);
        assertThrows(CustomException.class, () -> accountServiceImp.deleteAccountById(null));
    }

    @Test
    public void testupdateAccount() {
        Integer id = 1;
        UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest();
        updateAccountRequest.setAccountNumber("4567");
        updateAccountRequest.setFullName("rushikesh");
        updateAccountRequest.setStatus(Status.ACTIVE);


        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1);
        accountEntity.setFullName("sachin");
        accountEntity.setBalance(BigDecimal.valueOf(3444.789));
        accountEntity.setStatus(Status.ACTIVE);

        AccountMapper accountMapper = new AccountMapperImpl();
        AccountRepository accountMock = Mockito.mock(AccountRepository.class);
        BranchServiceImp branchServiceMock = Mockito.mock(BranchServiceImp.class);
        AccountServiceImp accountServiceImp = new AccountServiceImp(accountMapper, accountMock, branchServiceMock);
        Mockito.when(accountMock.findById(id)).thenReturn(Optional.of(accountEntity));
        accountEntity.setFullName("rushikesh");
        Mockito.when(accountMock.save(accountEntity)).thenReturn(accountEntity);
        AccountResponse accountResponse = accountServiceImp.updateAccount(id, updateAccountRequest);
        assertEquals("rushikesh", accountResponse.getFullName());
    }

    @Test
    public void testupdateAccountIfUpdateRequestNull() {
        Integer id = 1;
        AccountMapper accountMapper = new AccountMapperImpl();
        AccountRepository accountMock = Mockito.mock(AccountRepository.class);
        BranchServiceImp branchServiceMock = Mockito.mock(BranchServiceImp.class);
        AccountServiceImp accountServiceImp = new AccountServiceImp(accountMapper, accountMock, branchServiceMock);
      assertThrows(CustomException.class,()->accountServiceImp.updateAccount(id,null));
    }


}