package com.example.ecoomerce.user.dto.convertor;

import com.example.ecoomerce.user.dto.UserDto;
import com.example.ecoomerce.user.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConvertor {

    public UserDto toDto(User user)
    {
        return new UserDto(user.getMail(),user.getFirstName(),user.getLastName(),user.getPhoneNumber());
    }

    public List<UserDto> toDtoList(List<User> user)
    {

        return  user.stream().map(x-> new UserDto(x.getMail(), x.getFirstName(),x.getLastName(),x.getPhoneNumber())).collect(Collectors.toList());

    }

}
