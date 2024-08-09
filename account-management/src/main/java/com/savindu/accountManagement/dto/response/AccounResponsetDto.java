package com.savindu.accountManagement.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class AccounResponsetDto {
    @JsonProperty("account_number")
    private Long accountNumber;

    @JsonProperty("account_type")
    private String accountType;

    @JsonProperty("branch_address")
    private String branchAddress;
}
