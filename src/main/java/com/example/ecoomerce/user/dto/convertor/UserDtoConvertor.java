package com.example.ecoomerce.user.dto.convertor;

import com.example.ecoomerce.user.dto.UserDto;
import com.example.ecoomerce.user.model.CommunicationInfo;
import com.example.ecoomerce.user.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConvertor {

    private final CommunicationInfoDtoConvertor communicationInfoDtoConvertor;

    public UserDtoConvertor(CommunicationInfoDtoConvertor communicationInfoDtoConvertor) {
        this.communicationInfoDtoConvertor = communicationInfoDtoConvertor;
    }


    public UserDto toDto(User user)
    {

        return new UserDto(user.getMail(),user.getFirstName(),user.getLastName(),user.getPhoneNumber(),communicationInfoDtoConvertor.toDtoList(user.getCommunicationInfoList()));
    }

    public List<UserDto> toDtoList(List<User> user)
    {
        return  user.stream().map(this::toDto).collect(Collectors.toList());

    }

}
