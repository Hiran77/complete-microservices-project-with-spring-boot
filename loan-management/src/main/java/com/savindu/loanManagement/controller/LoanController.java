package com.savindu.loanManagement.controller;


import com.savindu.LoanURI;
import com.savindu.Patterns;
import com.savindu.URIPrefix;
import com.savindu.loanManagement.constant.LoanConstant;
import com.savindu.loanManagement.dto.LoanDto;
import com.savindu.loanManagement.dto.ResponseDto;
import com.savindu.loanManagement.service.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Loan", description = "Loan Management")
@RestController
@RequestMapping(path = URIPrefix.API+ LoanURI.LOAN, produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoanController {
    @Autowired

    private ILoanService iLoanService;

    @Operation(
            summary = "Create Loan REST API",
            description = "REST API to create new loan inside EazyBank"
    )

    @PostMapping(URIPrefix.CREATE)
    public ResponseEntity<ResponseDto> createLoan(@RequestParam
                                                  @Pattern(regexp = Patterns.NIC_NUMBER, message = "NIC number is invalid")
                                                  String nicNumber) {
        iLoanService.createLoan(nicNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoanConstant.STATUS_201, LoanConstant.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Loan Details REST API",
            description = "REST API to fetch loan details based on a mobile number"
    )
    @GetMapping(URIPrefix.GET)
    public ResponseEntity<LoanDto> fetchLoanDetails(@RequestParam
                                                     @Pattern(regexp = Patterns.NIC_NUMBER, message = "NIC number is invalid")
                                                     String nicNumber) {
        LoanDto loansDto = iLoanService.fetchLoan(nicNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @Operation(
            summary = "Update Loan Details REST API",
            description = "REST API to update loan details based on a loan number"
    )

    @PutMapping(URIPrefix.UPDATE)
    public ResponseEntity<ResponseDto> updateLoanDetails(@Valid @RequestBody LoanDto loansDto) {
        boolean isUpdated = iLoanService.updateLoan(loansDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoanConstant.STATUS_200, LoanConstant.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoanConstant.STATUS_417, LoanConstant.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Loan Details REST API",
            description = "REST API to delete Loan details based on a mobile number"
    )
    @DeleteMapping(URIPrefix.DELETE)
    public ResponseEntity<ResponseDto> deleteLoanDetails(@RequestParam
                                                         @Pattern(regexp = Patterns.NIC_NUMBER, message = "NIC number is invalid")
                                                         String nicNumber) {
        boolean isDeleted = iLoanService.deleteLoan(nicNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoanConstant.STATUS_200, LoanConstant.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoanConstant.STATUS_417, LoanConstant.MESSAGE_417_DELETE));
        }
    }

}
