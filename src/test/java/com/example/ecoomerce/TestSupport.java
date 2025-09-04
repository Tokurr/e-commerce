package com.example.ecoomerce;

import com.example.ecoomerce.user.dto.CommunicationInfoDto;
import com.example.ecoomerce.user.dto.UserDto;
import com.example.ecoomerce.user.model.User;

import java.util.ArrayList;
import java.util.List;

public class TestSupport {

    public List<User> generateUserList()
    {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            User generatedUser = new User((long) i,"exa" + i +"mple@outlook.com","tayyib" +i,"okur" +i,"0536591376" +i,true);
            userList.add(generatedUser);
        }

        return userList;
    }
    public List<UserDto> generateUserDtoList()
    {
        List<UserDto> userDtoList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<CommunicationInfoDto>  communicationInfoDto = new ArrayList<>();
            communicationInfoDto.add(new CommunicationInfoDto("adres1","adres2","adres3","adres4"));
            UserDto generatedUserDto = new UserDto("exa" + i +"mple@outlook.com","tayyib" +i,"okur" +i,"0536591376" +i,communicationInfoDto);
            userDtoList.add(generatedUserDto);
        }

        return userDtoList;
    }




}
