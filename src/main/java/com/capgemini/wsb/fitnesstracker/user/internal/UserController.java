package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserEmailDto;
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
    @GetMapping("/{userId}")
    public List<UserDto> getUser(@PathVariable Long userId){
        return userService.getUser(userId)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
    //dodaje użytkownika
    @PostMapping("/adduser")
    public User addUser(@RequestBody UserDto userDto){
        return userService.createUser(userMapper.toEntitySave(userDto));
    }

    //usuwa po id
    @ResponseStatus(OK)
    @DeleteMapping("/{userId}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(userId,OK);
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