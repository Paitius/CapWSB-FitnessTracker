package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDetailsDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserEmailDto;

import com.capgemini.wsb.fitnesstracker.user.api.UserSimpleDto;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static org.springframework.http.HttpStatus.*;

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
    @GetMapping("/{id}")
    public UserDetailsDto getUser(@PathVariable Long id){
        return userService.getUser(id).map(userMapper::userDetailsDto).orElse(null);

    }
    //dodaje użytkownika
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public User addUser(@RequestBody UserDto userDto){
        return userService.createUser(userMapper.toEntitySave(userDto));
    }

    //usuwa po id
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{userId}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(userId,NO_CONTENT);
    }
    //szuka po email
    @GetMapping("/email")
    public List<UserEmailDto> getUserByEmail(@RequestParam String email){
        return userService.getUserByEmail(email)
                .stream()
                .map(userMapper::userEmailDto)
                .toList();
    }

    @ResponseStatus(ACCEPTED)
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody UserDto changedUser) {
        return new ResponseEntity<>(userService.updateUser(userId, userMapper.toEntitySave(changedUser)), ACCEPTED);
    }
}