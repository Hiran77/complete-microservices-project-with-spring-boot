package com.savindu.account_management.service;

import com.savindu.account_management.dto.request.CustomerRequestDto;
import com.savindu.account_management.dto.request.UpdateCustomerRequestDto;
import com.savindu.account_management.dto.response.CustomerResponseDto;

public interface IAccountService {
    void createAccount(CustomerRequestDto customerDto);

    CustomerResponseDto fetch(String nicNumber);
    boolean updateAccount(UpdateCustomerRequestDto customerDto);
    boolean deleteAccount(String nicNumber);
}
