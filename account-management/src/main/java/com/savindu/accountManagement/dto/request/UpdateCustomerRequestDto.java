package com.savindu.accountManagement.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savindu.Patterns;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCustomerRequestDto {
    @NotEmpty(message = "Name is required")
    @Size(min = 3, message = "Name should have at least 3 characters")
    private String name;
    @NotEmpty(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;
    @NotEmpty(message = "Mobile is required")
    @Pattern(regexp = Patterns.MOBILE, message = "Mobile is invalid")
    private String mobile;

    @NotEmpty(message = "NIC number is required")
    @Pattern(regexp = Patterns.NIC_NUMBER, message = "NIC number is invalid")
    @JsonProperty("nic_number")
    private String nicNumber;


    private AccountRequestDto account;
}
