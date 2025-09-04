package com.example.ecoomerce.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @Pattern(
            regexp = "^(\\+90|0)?5\\d{9}$",
            message = "Please provide a valid phone number (e.g., 05554443322)"
    )
    private String phoneNumber;

}
