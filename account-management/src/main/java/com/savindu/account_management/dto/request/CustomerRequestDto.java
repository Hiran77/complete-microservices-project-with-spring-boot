package com.savindu.account_management.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(
        name = "Customer",
        description = "Request object for creating a new customer"

)
public class CustomerRequestDto {
    @NotEmpty(message = "Name is required")
    @Size(min = 3, message = "Name should have at least 3 characters")
    private String name;
    @NotEmpty(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;
    @NotEmpty(message = "Mobile is required")
    @Pattern(regexp = "^(?:\\+94|0)7\\d{8}$", message = "Mobile is invalid")
    private String mobile;
    @NotEmpty(message = "NIC number is required")
    @Pattern(regexp = "^(\\d{9}[vV]|\\d{12})$", message = "NIC number is invalid")
    private String nicNumber;


}
