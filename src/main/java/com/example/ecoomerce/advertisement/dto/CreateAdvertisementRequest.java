package com.example.ecoomerce.advertisement.dto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdvertisementRequest {

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "UserId cannot be null")
    private Long userId;

    @NotEmpty(message = "At least one hashtag must be provided")
    private Set<@NotBlank(message = "Hashtag cannot be blank") String> hashtag = new HashSet<>();
}