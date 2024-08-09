package com.savindu.account_management.controller;

import com.savindu.account_management.constant.AccountConstant;
import com.savindu.account_management.dto.request.CustomerRequestDto;
import com.savindu.account_management.dto.request.UpdateCustomerRequestDto;
import com.savindu.account_management.dto.response.CustomerResponseDto;
import com.savindu.account_management.dto.response.ResponseDto;
import com.savindu.account_management.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Tag(name = "Account", description = "Account Management")
@RestController
@RequestMapping(path = "/api",produces ={MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountController {
    private IAccountService iAccountService;
    @Operation(summary = "Create Account REST Endpoint",
            description = "This endpoint is used to create account",
            tags = {"Account"})

    @PostMapping("/create-account")
    public ResponseEntity<ResponseDto> createAccount(@Valid  @RequestBody CustomerRequestDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstant.STATUS_201, AccountConstant.MESSAGE_201));
    }
    @Operation(summary = "Fetch Account Details REST Endpoint",
            description = "This endpoint is used to fetch account details",
            tags = {"Account"})
    @GetMapping("/fetch/{mobileNumber}")
    public ResponseEntity<CustomerResponseDto> fetchAccountDetails(@PathVariable
                                                                       @Pattern(regexp = "^(\\d{9}[vV]|\\d{12})$", message = "NIC number is invalid")
                                                               String nicNumber){
        CustomerResponseDto fetch = iAccountService.fetch(nicNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fetch);
    }
    @Operation(summary = "Update Account REST Endpoint",
            description = "This endpoint is used to update account",
            tags = {"Account"})
    @PutMapping("/update-account")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody UpdateCustomerRequestDto customerDto){
        boolean updateAccount = iAccountService.updateAccount(customerDto);
        if(updateAccount){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                .body(new ResponseDto(AccountConstant.STATUS_500, AccountConstant.MESSAGE_500));
    }
    @Operation(summary = "Delete Account REST Endpoint",
            description = "This endpoint is used to delete account",
            tags = {"Account"})
    @DeleteMapping("/delete/{mobileNumber}")
    public ResponseEntity<ResponseDto> deleteAccount(@PathVariable
                                                         @Pattern(regexp = "^(\\d{9}[vV]|\\d{12})$", message = "NIC number is invalid")
                                                         String nicNumber){
        boolean b = iAccountService.deleteAccount(nicNumber);
        if(b){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .body(new ResponseDto(AccountConstant.STATUS_500, AccountConstant.MESSAGE_500));
        }
    }
}
