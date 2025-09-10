package com.example.ecoomerce.advertisement.dto;
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

    String title;
    String description;
    BigDecimal price;
    Long userId;
    Set<String> hashtag = new HashSet<>();

}
