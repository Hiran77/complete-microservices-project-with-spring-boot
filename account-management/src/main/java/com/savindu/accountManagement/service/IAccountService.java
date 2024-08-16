package com.savindu.accountManagement.service;

import com.savindu.accountManagement.dto.request.CustomerRequestDto;
import com.savindu.accountManagement.dto.request.UpdateCustomerRequestDto;
import com.savindu.accountManagement.dto.response.CustomerResponseDto;
import com.savindu.accountManagement.dto.response.ResponseDto;

public interface IAccountService {
    void createAccount(CustomerRequestDto customerDto);

    CustomerResponseDto fetch(String nicNumber);
    boolean updateAccount(UpdateCustomerRequestDto customerDto);
    boolean deleteAccount(String nicNumber);

    String getActiveProfile();
}
