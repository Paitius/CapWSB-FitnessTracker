package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserSimpleDto;
import com.capgemini.wsb.fitnesstracker.user.internal.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }
    // Wylistowanie uzytkownikow zawierajac tylko id, imie i nazwisko
    @GetMapping("/simple")
    public List<UserSimpleDto> getUsersSimple(){
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toUserSimpleDto)
                .toList();
    }
    //Zwraca uzytkownika o podanym id
    @GetMapping("/{userId}")
    public List<UserDto> getUser(@PathVariable Long userId){
        return userService.getUser(userId)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping("/adduser")
    public User addUser(@RequestBody UserDto userDto){
        User user = userService.createUser(userMapper.toEntitySave(userDto));
        return user;
    }


}