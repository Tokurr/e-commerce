package com.example.ecoomerce.user.service;

import com.example.ecoomerce.exception.UserNotFoundException;
import com.example.ecoomerce.user.dto.CreateUserRequest;
import com.example.ecoomerce.user.dto.UpdateUserRequest;
import com.example.ecoomerce.user.dto.UserDto;
import com.example.ecoomerce.user.dto.convertor.UserDtoConvertor;
import com.example.ecoomerce.user.model.User;
import com.example.ecoomerce.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.net.CacheRequest;
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

    public UserDto createUser (CreateUserRequest createUserRequest)
    {
        User user = new User(createUserRequest.getMail(),createUserRequest.getFirstName(),createUserRequest.getLastName(),createUserRequest.getPhoneNumber());
        return userDtoConvertor.toDto(userRepository.save(user));
    }

    public UserDto updateUser(String mail,UpdateUserRequest updateUserRequest)
    {
        User user = userRepository.findUserByMail(mail).orElseThrow(() -> new UserNotFoundException("User not found with mail: " + mail));
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setPhoneNumber(updateUserRequest.getPhoneNumber());
        return userDtoConvertor.toDto(userRepository.save(user));
    }

}
