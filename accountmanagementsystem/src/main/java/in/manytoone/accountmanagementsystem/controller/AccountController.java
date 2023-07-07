package in.manytoone.accountmanagementsystem.controller;

import in.manytoone.accountmanagementsystem.dto.request.CreateAccountRequest;
import in.manytoone.accountmanagementsystem.dto.request.UpdateAccountRequest;
import in.manytoone.accountmanagementsystem.dto.response.AccountResponse;
import in.manytoone.accountmanagementsystem.dto.response.ApiResponse;
import in.manytoone.accountmanagementsystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

   private final AccountService accountService;
    @PostMapping("/accounts")
    public ApiResponse<AccountResponse> saveAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest){
        log.debug("<<<<<<<<< saveAccount()");
        AccountResponse accountResponse = this.accountService.creatAccount(createAccountRequest);
        log.debug("saveAccount() >>>>>>>");
        ApiResponse.ApiResponseBuilder<AccountResponse> builder = ApiResponse.builder();
        return builder.data(accountResponse).statusCode(HttpStatus.CREATED.value()).build();
    }

    @GetMapping("/accounts/{id}")
    public ApiResponse<AccountResponse> fetchAccountById(@PathVariable("id") @Positive(message= "id should be greater than zero") @NotNull(message="id should not be null or empty") Integer id){
        log.debug("<<<<<<<<< fetchAccountById()");
        AccountResponse account = this.accountService.getAccountById(id);
        log.debug("fetchAccountById() >>>>>>>");
        ApiResponse.ApiResponseBuilder<AccountResponse> builder = ApiResponse.builder();
        return builder.data(account).statusCode(HttpStatus.CREATED.value()).build();
    }


    @DeleteMapping("/accounts/{id}")
    public ApiResponse<Boolean> deleteAccountById(@PathVariable("id") @Positive(message= "id should be greater than zero") @NotNull(message="id should not be null or empty")  Integer id)
    {
        log.debug("<<<<<<<<< deleteAccountById()");
       this.accountService.deleteAccountById(id);
        log.debug("deleteAccountById() >>>>>>>");
        ApiResponse.ApiResponseBuilder<Boolean> builder = ApiResponse.builder();
        return builder.data(true).statusCode(HttpStatus.CREATED.value()).build();
    }

    @PostMapping("/accounts/{id}")
    public ApiResponse<AccountResponse> updateAccount(@PathVariable("id")  @Positive(message= "id should be greater than zero") @NotNull(message="id should not be null or empty")   Integer id, @Valid @RequestBody UpdateAccountRequest updateAccountRequest){
        log.debug("<<<<<<<<< updateAccount()");
        AccountResponse accountResponse = this.accountService.updateAccount(id, updateAccountRequest);
        log.debug("updateAccount() >>>>>>>");
        ApiResponse.ApiResponseBuilder<AccountResponse> builder = ApiResponse.builder();
        return builder.data(accountResponse).statusCode(HttpStatus.OK.value()).build();
    }





}
