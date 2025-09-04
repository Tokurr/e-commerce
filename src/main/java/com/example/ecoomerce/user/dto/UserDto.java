package com.example.ecoomerce.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    String mail;
    String firstName;
    String lastName;
    String phoneNumber;
    List<CommunicationInfoDto> communicationInfoDtoList;


}
