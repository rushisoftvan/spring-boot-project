package in.manytoone.accountmanagementsystem.service;

import in.manytoone.accountmanagementsystem.dto.request.CreateAccountRequest;
import in.manytoone.accountmanagementsystem.dto.request.UpdateAccountRequest;
import in.manytoone.accountmanagementsystem.dto.response.AccountResponse;

public interface AccountService {

    public AccountResponse creatAccount(CreateAccountRequest createAccountRequest);
    public AccountResponse getAccountById(Integer id);

    public Boolean deleteAccountById(Integer id);

    public AccountResponse updateAccount(Integer id, UpdateAccountRequest updateAccountRequest);


}
