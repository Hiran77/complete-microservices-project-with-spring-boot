package com.savindu.account_management.service;

import com.savindnu.MicroservicePractice.dto.request.CustomerRequestDto;
import com.savindnu.MicroservicePractice.dto.request.UpdateCustomerRequestDto;
import com.savindnu.MicroservicePractice.dto.response.CustomerResponseDto;

public interface IAccountService {
    void createAccount(CustomerRequestDto customerDto);

    CustomerResponseDto fetch(String nicNumber);
    boolean updateAccount(UpdateCustomerRequestDto customerDto);
    boolean deleteAccount(String nicNumber);
}
