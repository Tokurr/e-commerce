package com.example.ecoomerce.user.service;

import com.example.ecoomerce.exception.UserNotFoundException;
import com.example.ecoomerce.user.dto.UserDto;
import com.example.ecoomerce.user.dto.convertor.UserDtoConvertor;
import com.example.ecoomerce.user.model.User;
import com.example.ecoomerce.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public UserDto getUserByMail(String mail)
    {
        User user = userRepository.findUserByMail(mail).orElseThrow(() -> new UserNotFoundException("User not found with mail: " + mail));
        return userDtoConvertor.toDto(user);
    }

}
