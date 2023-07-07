package in.manytoone.accountmanagementsystem.controller;

import in.manytoone.accountmanagementsystem.dto.request.CreateAccountRequest;
import in.manytoone.accountmanagementsystem.dto.response.AccountResponse;
import in.manytoone.accountmanagementsystem.dto.response.ApiResponse;
import in.manytoone.accountmanagementsystem.entity.BranchEntity;
import in.manytoone.accountmanagementsystem.enums.Status;
import in.manytoone.accountmanagementsystem.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private AccountController accountController;

    @Test
    public void  testSaveAccount(){
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setAccountNumber("464744");
        createAccountRequest.setFullName("sachin");
        createAccountRequest.setBalance(BigDecimal.valueOf(344454.000));
        createAccountRequest.setBranchId(1);

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountNumber("464744");
        accountResponse.setStatus(Status.ACTIVE);
        accountResponse.setFullName("sachin");
        accountResponse.setBranchName("odhav");
        accountResponse.setBalance(BigDecimal.valueOf(344454.000));

        Mockito.when(this.accountService.creatAccount(createAccountRequest)).thenReturn(accountResponse);
        ApiResponse<AccountResponse> accountResponseApiResponse = this.accountController.saveAccount(createAccountRequest);
        assertEquals("sachin",accountResponseApiResponse.getData().getFullName());
        assertEquals(HttpStatus.CREATED.value(),accountResponseApiResponse.getStatusCode());

    }




}