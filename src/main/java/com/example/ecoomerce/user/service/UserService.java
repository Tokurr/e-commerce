package com.example.ecoomerce.user.service;

import com.example.ecoomerce.user.dto.UserDto;
import com.example.ecoomerce.user.dto.convertor.UserDtoConvertor;
import com.example.ecoomerce.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConvertor userDtoConvertor;
    public UserService(UserRepository userRepository, UserDtoConvertor userDtoConvertor) {
        this.userRepository = userRepository;
        this.userDtoConvertor = userDtoConvertor;
    }


    public List<UserDto> getAllUsers()
    {
        return userRepository.findAll().stream().map(user -> userDtoConvertor.toDto(user)).collect(Collectors.toList());
    }

}
