package com.example.ecoomerce.user.service;

import com.example.ecoomerce.TestSupport;
import com.example.ecoomerce.exception.UserIsNotActiveException;
import com.example.ecoomerce.exception.UserNotFoundException;
import com.example.ecoomerce.user.dto.CreateUserRequest;
import com.example.ecoomerce.user.dto.UpdateUserRequest;
import com.example.ecoomerce.user.dto.UserDto;
import com.example.ecoomerce.user.dto.convertor.UserDtoConvertor;
import com.example.ecoomerce.user.model.User;
import com.example.ecoomerce.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

class UserServiceTest {


    private UserDtoConvertor convertor;
    private UserRepository userRepository;
    private UserService userService;

    TestSupport support = new TestSupport();

    @BeforeEach
    void setUp() {
        convertor = Mockito.mock(UserDtoConvertor.class);
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository,convertor);
    }

    @Test
    public void testGetAllUsers_itShouldReturnUserDtoList(){

        List<User> users ;
        List<UserDto> usersDto;

        usersDto = support.generateUserDtoList();
        users = support.generateUserList();


        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(convertor.toDtoList(users)).thenReturn(usersDto);

        List<UserDto> allUsers = userService.getAllUsers();

        Assertions.assertEquals(usersDto, allUsers);
        Mockito.verify(userRepository).findAll();
        Mockito.verify(convertor).toDtoList(users);

    }

    @Test
    public void testGetUserByMail_itShouldReturnUserDto()
    {
        String mail = "okurtayyib@outlook.com";

        User user = new User(1L,"okurtayyib@outlook.com","tayyib","okur","05439914532",true);
        UserDto userDto = new UserDto("okurtayyib@outlook.com","tayyib","okur","05439914532");
        Mockito.when(userRepository.findByMailOrThrow(mail)).thenReturn(user);
        Mockito.when(convertor.toDto(user)).thenReturn(userDto);
        UserDto returnUserDto = userService.getUserByMail(mail);

        Assertions.assertEquals(returnUserDto,userDto);

        Mockito.verify(userRepository).findByMailOrThrow(mail);
        Mockito.verify(convertor).toDto(user);


    }

    @Test
    public void testGetUserByMail_whenMailNotFound_itShouldThrowUserNotFoundException()
    {
        String mail = "okurtayyib@outlook.com";
        Mockito.when(userRepository.findByMailOrThrow(mail)).thenThrow(new UserNotFoundException("User not found with mail: " + mail));
        Assertions.assertThrows(UserNotFoundException.class, ()-> userService.getUserByMail(mail));
        Mockito.verify(convertor, never()).toDto(any(User.class));
    }


    @Test
    public void testCreateUser_whenValidRequest_itShouldReturnUserDto()
    {
        CreateUserRequest createUserRequest = new CreateUserRequest("okurtayyib@outlook.com","tayyib","okur","05439914532");
        User user = new User("okurtayyib@outlook.com","tayyib","okur","05439914532",true);
        User savedUser = new User(1L,"okurtayyib@outlook.com","tayyib","okur","05439914532",true);

        UserDto userDto = new UserDto("okurtayyib@outlook.com","tayyib","okur","05439914532");
        Mockito.when(userRepository.save(user)).thenReturn(savedUser);
        Mockito.when(convertor.toDto(savedUser)).thenReturn(userDto);

        UserDto result = userService.createUser(createUserRequest);

        Assertions.assertEquals(result,userDto);
        Mockito.verify(userRepository).save(user);
        Mockito.verify(convertor).toDto(savedUser);

    }

    @Test
    public void testUpdateUser_whenUserMailExistAndUserIsActive_itShouldReturnUpdateUserDto()
    {
        User user = new User(1L,"okurtayyib@outlook.com","tayyib","okur","05439914532",true);
        User savedUser = new User(1L,"okurtayyib@outlook.com","tayyib2","okur2","05439914532",true);
        UserDto userDto = new UserDto("okurtayyib@outlook.com","tayyib2","okur2","05439914532");

        String mail = "okurtayyib@outlook.com";
        UpdateUserRequest updateUserRequest = new UpdateUserRequest("tayyib","okur","05439914532");
        Mockito.when(userRepository.findByMailOrThrow(mail)).thenReturn(user);

        Mockito.when(userRepository.save(user)).thenReturn(savedUser);

        Mockito.when(convertor.toDto(savedUser)).thenReturn(userDto);

        UserDto result = userService.updateUser(mail,updateUserRequest);

        Assertions.assertEquals(result,userDto);

        Mockito.verify(userRepository).save(user);
        Mockito.verify(userRepository).findByMailOrThrow(mail);
        Mockito.verify(convertor).toDto(savedUser);

    }
    @Test
    public void testUpdateUser_whenUserMailDoesNotExist_itShouldReturnThrowUserNotFoundExcption()
    {
        String mail = "okurtayyib@outlook.com";
        UpdateUserRequest updateUserRequest = new UpdateUserRequest("tayyib","okur","05439914532");
        Mockito.when(userRepository.findByMailOrThrow(mail)).thenThrow(new UserNotFoundException("User not found with mail: " + mail));

        Assertions.assertThrows(UserNotFoundException.class, ()-> userService.updateUser(mail,updateUserRequest));

        Mockito.verify(userRepository).findByMailOrThrow(mail);


    }

    @Test
    public void testUpdateUser_whenUserMailExistButUserIsNotActive_itShouldReturnThrowUserIsNotActiveException()
    {
        User user = new User(1L,"okurtayyib@outlook.com","tayyib","okur","05439914532",false);

        String mail = "okurtayyib@outlook.com";
        UpdateUserRequest updateUserRequest = new UpdateUserRequest("tayyib","okur","05439914532");
        Mockito.when(userRepository.findByMailOrThrow(mail)).thenReturn(user);

        Assertions.assertThrows(UserIsNotActiveException.class, ()->userService.updateUser(mail, updateUserRequest));

        Mockito.verify(userRepository).findByMailOrThrow(mail);


    }

    @Test
    public void testDeactiveUser_whenUserIdExist_itShouldUpdateUserByActiveFalse()
    {
        Long id = 1L;
        User user = new User(1L,"okurtayyib@outlook.com","tayyib","okur","05439914532",true);
        Mockito.when(userRepository.findByIdOrThrow(id)).thenReturn(user);
        User updateUser = new User(1L,"okurtayyib@outlook.com","tayyib","okur","05439914532",false);

        userService.deactivateUser(id);
        Mockito.verify(userRepository).findByIdOrThrow(id);
        Mockito.verify(userRepository).save(updateUser);


    }


    @Test
    public void testDeactiveUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException()
    {
        Long id = 1L;

        Mockito.when(userRepository.findByIdOrThrow(id)).thenThrow(new UserNotFoundException("User not found with id: " + id));

        Assertions.assertThrows(UserNotFoundException.class, ()-> userService.deactivateUser(id));
        Mockito.verify(userRepository).findByIdOrThrow(id);
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testActiveUser_whenUserIdExist_itShouldUpdateUserByActiveTrue()
    {
        Long id = 1L;
        User user = new User(1L,"okurtayyib@outlook.com","tayyib","okur","05439914532",false);
        Mockito.when(userRepository.findByIdOrThrow(id)).thenReturn(user);
        User updateUser = new User(1L,"okurtayyib@outlook.com","tayyib","okur","05439914532",true);

        userService.activeUser(id);
        Mockito.verify(userRepository).findByIdOrThrow(id);
        Mockito.verify(userRepository).save(updateUser);


    }


    @Test
    public void testActiveUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException()
    {
        Long id = 1L;

        Mockito.when(userRepository.findByIdOrThrow(id)).thenThrow(new UserNotFoundException("User not found with id: " + id));

        Assertions.assertThrows(UserNotFoundException.class, ()-> userService.activeUser(id));
        Mockito.verify(userRepository).findByIdOrThrow(id);
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testDeleteUser_whenUserIdExist_itShouldDeleteById()
    {
        Long id = 1l;

        Mockito.when(userRepository.existsById(id)).thenReturn(true);

        userService.deleteUser(id);
        Mockito.verify(userRepository).deleteById(id);
        Mockito.verify(userRepository).existsById(id);

    }

    @Test
    public void testDeleteUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException()
    {
        Long id = 1l;

        Mockito.when(userRepository.existsById(id)).thenReturn(false);
        Assertions.assertThrows(UserNotFoundException.class,()->userService.deleteUser(id));
        Mockito.verify(userRepository).existsById(id);
        Mockito.verifyNoMoreInteractions(userRepository);
    }



    @AfterEach
    void tearDown() {
    }





}