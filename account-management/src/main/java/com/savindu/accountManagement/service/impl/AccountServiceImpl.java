package com.savindu.accountManagement.service.impl;

import com.savindu.accountManagement.constant.AccountConstant;
import com.savindu.accountManagement.dto.request.AccountRequestDto;
import com.savindu.accountManagement.dto.request.CustomerRequestDto;
import com.savindu.accountManagement.dto.request.UpdateCustomerRequestDto;
import com.savindu.accountManagement.dto.response.CustomerResponseDto;
import com.savindu.accountManagement.dto.response.ResponseDto;
import com.savindu.accountManagement.entity.Account;
import com.savindu.accountManagement.entity.Customer;
import com.savindu.accountManagement.exception.CustomerAlreadyException;
import com.savindu.accountManagement.exception.ResourceNotFoundException;
import com.savindu.accountManagement.mapper.CustomerMapper;
import com.savindu.accountManagement.repository.AccountRepository;
import com.savindu.accountManagement.repository.CustomerRepository;
import com.savindu.accountManagement.service.IAccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Value("${application.active.profile}")
    private  String activeProfile;

    @Override
    public void createAccount(CustomerRequestDto customerDto) {
        Customer customer = CustomerMapper.INSTANCE.toEntity(customerDto);
        if(customerRepository.findByNicNumber(customer.getNicNumber()).isPresent()){
            throw new CustomerAlreadyException("Customer already registered with given NIC number: "
                    +customer.getNicNumber()
                    +" Please try with different NIC number");
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
    @Transactional
    public boolean updateAccount(UpdateCustomerRequestDto customerDto) {
        boolean isUpdated = false;
        AccountRequestDto accounResponsetDto = customerDto.getAccount();
        if(accounResponsetDto !=null){
            Account account = accountRepository.findByAccountNumber(accounResponsetDto.getAccountNumber())
                    .orElseThrow(
                            ()->new ResourceNotFoundException("Account", "NIC Number", customerDto.getNicNumber())
                    );
            account.setAccountType(accounResponsetDto.getAccountType());
            account.setBranchAddress(accounResponsetDto.getBranchAddress());
            accountRepository.save(account);

            Long customerId = account.getCustomer().getId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(
                            ()->new ResourceNotFoundException("Account", "NIC Number", customerDto.getNicNumber())
                    );
            customer.setEmail(customerDto.getEmail());
            customer.setName(customerDto.getName());
            customer.setMobile(customerDto.getMobile());
            customer.setNicNumber(customerDto.getNicNumber());
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

    @Override
    public String getActiveProfile() {
        return activeProfile;
    }


}
