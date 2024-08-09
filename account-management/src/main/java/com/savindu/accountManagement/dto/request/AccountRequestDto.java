package com.savindu.accountManagement.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savindu.Patterns;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(
        name = "Account",
        description = "Request object for creating a new account"
)
public class AccountRequestDto {
    @NotEmpty(message = "Account number is required")
    @Pattern(regexp = Patterns.ACCOUNT_NUMBER, message = "Account number is invalid")
    @JsonProperty("account_number")
    private Long accountNumber;
    @NotEmpty(message = "Account type is required")
    @JsonProperty("account_type")
    private String accountType;
    @NotEmpty(message = "Branch address is required")
    @JsonProperty("branch_address")
    private String branchAddress;
}
