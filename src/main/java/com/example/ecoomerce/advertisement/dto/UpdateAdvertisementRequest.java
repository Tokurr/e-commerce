package com.example.ecoomerce.advertisement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateAdvertisementRequest {

    String title;
    String description;
    BigDecimal price;
    Set<String> hashtag = new HashSet<>();

}
