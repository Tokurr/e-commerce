package com.example.ecoomerce;

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

            UserDto generatedUserDto = new UserDto("exa" + i +"mple@outlook.com","tayyib" +i,"okur" +i,"0536591376" +i);
            userDtoList.add(generatedUserDto);
        }

        return userDtoList;
    }




}
