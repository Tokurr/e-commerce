package com.example.ecoomerce.user.dto.convertor;

import com.example.ecoomerce.user.dto.UserDto;
import com.example.ecoomerce.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConvertor {

    public UserDto toDto(User user)
    {
        return new UserDto(user.getMail(),user.getFirstName(),user.getLastName(),user.getPhoneNumber());
    }

}
