package com.example.ecoomerce.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommunicationInfoRequest {

    @NotNull(message = "UserId cannot be null")
    private Long userId;

    @NotBlank(message = "Address cannot be empty")
    private String address;

    @NotBlank(message = "City cannot be empty")
    private String city;

    @NotBlank(message = "Country cannot be empty")
    private String country;

    @NotBlank(message = "Post code cannot be empty")
    private String postCode;


}
