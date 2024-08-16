package com.savindu.accountManagement.controller;

import com.savindu.AccountURI;
import com.savindu.Patterns;
import com.savindu.URIPrefix;
import com.savindu.accountManagement.constant.AccountConstant;
import com.savindu.accountManagement.dto.request.CustomerRequestDto;
import com.savindu.accountManagement.dto.request.UpdateCustomerRequestDto;
import com.savindu.accountManagement.dto.response.CustomerResponseDto;
import com.savindu.accountManagement.dto.response.ResponseDto;
import com.savindu.accountManagement.service.IAccountService;
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
@RequestMapping(path = URIPrefix.API+AccountURI.ACCOUNT,produces ={MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountController {
    private IAccountService iAccountService;
    @Operation(summary = "Create Account REST Endpoint",
            description = "This endpoint is used to create account",
            tags = {"Account"})

    @PostMapping(URIPrefix.CREATE)
    public ResponseEntity<ResponseDto> createAccount(@Valid  @RequestBody CustomerRequestDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstant.STATUS_201, AccountConstant.MESSAGE_201));
    }
    @Operation(summary = "Fetch Account Details REST Endpoint",
            description = "This endpoint is used to fetch account details",
            tags = {"Account"})
    @GetMapping(URIPrefix.GET+ AccountURI.NIC_NUMBER)
    public ResponseEntity<CustomerResponseDto> fetchAccountDetails(@PathVariable
                                                                       @Pattern(regexp = Patterns.NIC_NUMBER, message = "NIC number is invalid")
                                                                       String nicNumber){
        CustomerResponseDto fetch = iAccountService.fetch(nicNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fetch);
    }
    @Operation(summary = "Update Account REST Endpoint",
            description = "This endpoint is used to update account",
            tags = {"Account"})
    @PutMapping(URIPrefix.UPDATE)
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
    @DeleteMapping(URIPrefix.DELETE+ AccountURI.NIC_NUMBER)
    public ResponseEntity<ResponseDto> deleteAccount(@PathVariable
                                                         @Pattern(regexp = Patterns.NIC_NUMBER, message = "NIC number is invalid")
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
    @GetMapping(URIPrefix.GET+ "/active-profile")
    public ResponseEntity<String> getActiveProfile(){
        String fetch = iAccountService.getActiveProfile();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fetch);
    }
}
