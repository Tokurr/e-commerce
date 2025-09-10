package com.example.ecoomerce.advertisement.dto;

import com.example.ecoomerce.user.dto.UserDto;
import com.example.ecoomerce.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdvertisementDto {
    
    
    UUID id;
    String title;
    String description;
    BigDecimal price;
    LocalDateTime creationDate;
    LocalDateTime lastModifiedDate;
    UserDto user;
    Set<String> hashtags;

}
