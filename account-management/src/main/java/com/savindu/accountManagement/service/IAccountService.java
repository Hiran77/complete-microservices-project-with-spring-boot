package com.savindu.accountManagement.service;

import com.savindu.accountManagement.dto.request.CustomerRequestDto;
import com.savindu.accountManagement.dto.request.UpdateCustomerRequestDto;
import com.savindu.accountManagement.dto.response.CustomerResponseDto;

public interface IAccountService {
    void createAccount(CustomerRequestDto customerDto);

    CustomerResponseDto fetch(String nicNumber);
    boolean updateAccount(UpdateCustomerRequestDto customerDto);
    boolean deleteAccount(String nicNumber);
}
