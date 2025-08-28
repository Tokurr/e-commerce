package com.example.ecoomerce.user.controller;

import com.example.ecoomerce.user.dto.UserDto;
import com.example.ecoomerce.user.dto.convertor.UserDtoConvertor;
import com.example.ecoomerce.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{mail}")
    public ResponseEntity<UserDto> getUserByMail(String mail)
    {
       return ResponseEntity.ok(userService.getUserByMail(mail));
    }



}
