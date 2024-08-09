package com.savindu.accountManagement.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerResponseDto {

    private String name;

    private String email;

    private String mobile;
    @JsonProperty("nic_number")
    private String nicNumber;
    private AccounResponsetDto account;

}
