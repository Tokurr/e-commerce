package com.example.ecoomerce.user.service;

import com.example.ecoomerce.exception.UserIsNotActiveException;
import com.example.ecoomerce.exception.UserNotFoundException;
import com.example.ecoomerce.user.dto.CreateUserRequest;
import com.example.ecoomerce.user.dto.UpdateUserRequest;
import com.example.ecoomerce.user.dto.UserDto;
import com.example.ecoomerce.user.dto.convertor.UserDtoConvertor;
import com.example.ecoomerce.user.model.User;
import com.example.ecoomerce.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;


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
        return  userDtoConvertor.toDtoList(userRepository.findAll());
    }

    public UserDto getUserByMail(String mail)
    {
        User user = userRepository.findByMailOrThrow(mail);
        return userDtoConvertor.toDto(user);
    }

    public UserDto createUser (CreateUserRequest createUserRequest)
    {
        User user = new User(createUserRequest.getMail(),createUserRequest.getFirstName(),createUserRequest.getLastName(),createUserRequest.getPhoneNumber(),true);
        return userDtoConvertor.toDto(userRepository.save(user));
    }

    public UserDto updateUser(String mail,UpdateUserRequest updateUserRequest)
    {
        User user = userRepository.findByMailOrThrow(mail);

        if(!user.isActive())
        {
            throw new UserIsNotActiveException("The user wanted update is not active!");

        }
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setPhoneNumber(updateUserRequest.getPhoneNumber());
        return userDtoConvertor.toDto(userRepository.save(user));
    }

    public void deactivateUser(Long id)
    {
        User user = userRepository.findByIdOrThrow(id);

        User updateUser = new User(user.getId(),
                user.getMail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                false);

       userRepository.save(updateUser);

    }

    public void activeUser(Long id){
        User user = userRepository.findByIdOrThrow(id);

        User updateUser = new User(user.getId(),
                user.getMail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                true);

        userRepository.save(updateUser);
    }

     public void deleteUser(Long id)
    {

        if(userRepository.existsById(id))
        {
            userRepository.deleteById(id);

        }
        else
        {
            throw new UserNotFoundException("User not found with id: " + id);

        }
    }


}
