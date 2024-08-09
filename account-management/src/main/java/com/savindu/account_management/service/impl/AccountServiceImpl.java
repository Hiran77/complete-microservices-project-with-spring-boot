package com.savindu.account_management.service.impl;

import com.savindnu.MicroservicePractice.constant.AccountConstant;
import com.savindnu.MicroservicePractice.dto.request.AccountRequestDto;
import com.savindnu.MicroservicePractice.dto.request.CustomerRequestDto;
import com.savindnu.MicroservicePractice.dto.request.UpdateCustomerRequestDto;
import com.savindnu.MicroservicePractice.dto.response.CustomerResponseDto;
import com.savindnu.MicroservicePractice.entity.Account;
import com.savindnu.MicroservicePractice.entity.Customer;
import com.savindnu.MicroservicePractice.exception.CustomerAlreadyException;
import com.savindnu.MicroservicePractice.exception.ResourceNotFoundException;
import com.savindnu.MicroservicePractice.mapper.CustomerMapper;
import com.savindnu.MicroservicePractice.repository.AccountRepository;
import com.savindnu.MicroservicePractice.repository.CustomerRepository;
import com.savindnu.MicroservicePractice.service.IAccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerRequestDto customerDto) {
        Customer customer = CustomerMapper.INSTANCE.toEntity(customerDto);
        if(customerRepository.findByMobile(customer.getMobile()).isPresent()){
            throw new CustomerAlreadyException("Customer already registered with given mobile number: "
                    +customer.getMobile()
                    +" Please try with different mobile number");
        }
        Customer save = customerRepository.save(customer);
        accountRepository.save(createAccount(save));


    }
    private Account createAccount(Customer customer){
        Account account = new Account();

        account.setCustomer(customer);
        account.setAccountNumber(1000000000L+new Random().nextInt(900000000));
        account.setAccountType(AccountConstant.SAVINGS);
        account.setBranchAddress(AccountConstant.ADDRESS);
        return account;
    }

    @Override
    public CustomerResponseDto fetch(String nicNumber) {
        Customer customer = customerRepository.findByNicNumber(nicNumber)
                .orElseThrow(
                        ()->new ResourceNotFoundException("Customer", "nic number", nicNumber)
                );
        Account account = accountRepository.findByCustomerId(customer.getId())
                .orElseThrow(
                        ()->new ResourceNotFoundException("Account", "nic number", nicNumber)
                );

        return CustomerMapper.INSTANCE.toDto(customer);

    }

    @Override
    public boolean updateAccount(UpdateCustomerRequestDto customerDto) {
        boolean isUpdated = false;
        AccountRequestDto accounResponsetDto = customerDto.getAccount();
        if(accounResponsetDto !=null){
            Account account = accountRepository.findById(accounResponsetDto.getAccountNumber())
                    .orElseThrow(
                            ()->new ResourceNotFoundException("Account", "mobile number", customerDto.getMobile())
                    );
            account.setAccountType(accounResponsetDto.getAccountType());
            account.setBranchAddress(accounResponsetDto.getBranchAddress());
            account.setAccountNumber(accounResponsetDto.getAccountNumber());
            accountRepository.save(account);

            Long customerId = account.getCustomer().getId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(
                            ()->new ResourceNotFoundException("Customer", "mobile number", customerDto.getMobile())
                    );
            customer.setEmail(customerDto.getEmail());
            customer.setName(customerDto.getName());
            customer.setMobile(customerDto.getMobile());
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    @Transactional
    public boolean deleteAccount(String nicNumber) {
        boolean isUpdated = false;
        Customer customer = customerRepository.findByNicNumber(nicNumber)
                .orElseThrow(
                        ()->new ResourceNotFoundException("Customer", "nic number", nicNumber)
                );
        Account account = accountRepository.findByCustomerId(customer.getId())
                .orElseThrow(
                        ()->new ResourceNotFoundException("Account", "nic number", nicNumber)
                );
        accountRepository.delete(account);
        customerRepository.delete(customer);
        isUpdated = true;
        return isUpdated;

    }



}
