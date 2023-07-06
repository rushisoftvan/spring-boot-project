package in.manytoone.accountmanagementsystem.controller;

import in.manytoone.accountmanagementsystem.dto.request.CreateAccountRequest;
import in.manytoone.accountmanagementsystem.dto.response.AccountResponse;
import in.manytoone.accountmanagementsystem.dto.response.ApiResponse;
import in.manytoone.accountmanagementsystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

   private final AccountService accountService;
    @PostMapping("/accounts")
    public ApiResponse<AccountResponse> saveAccount(@RequestBody CreateAccountRequest createAccountRequest){
        AccountResponse accountResponse = this.accountService.creatAccount(createAccountRequest);
        ApiResponse.ApiResponseBuilder<AccountResponse> builder = ApiResponse.builder();
        return builder.data(accountResponse).statusCode(HttpStatus.CREATED.value()).build();
    }

    @GetMapping("/accounts/{id}")
    public ApiResponse<AccountResponse> fetchAccountById(@PathVariable("id") Integer id){
        AccountResponse account = this.accountService.getAccountById(id);
        ApiResponse.ApiResponseBuilder<AccountResponse> builder = ApiResponse.builder();
        return builder.data(account).statusCode(HttpStatus.CREATED.value()).build();
    }


    @DeleteMapping("/accounts/{id}")
    public ApiResponse<Boolean> deleteAccountById(@PathVariable Integer id)
    {
       this.accountService.deleteAccountById(id);
        ApiResponse.ApiResponseBuilder<Boolean> builder = ApiResponse.builder();
        return builder.data(true).statusCode(HttpStatus.CREATED.value()).build();
    }

}
