package com.example.ecoomerce.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCommunicationInfo {

    private  String address;
    private  String city;
    private  String country;
    private  String postCode;

}
