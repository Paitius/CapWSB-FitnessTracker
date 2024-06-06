package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDetailsDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserEmailDto;

import com.capgemini.wsb.fitnesstracker.user.api.UserSimpleDto;

import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    /**
     *  Get all users from DB and map them to UserDto objects
     * @return List of UserDto objects
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    /**
     *  Get all users from DB and map them to UserSimpleDto objects
     * @return List of UserSimpleDto objects
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getUsersSimple(){
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toUserSimpleDto)
                .toList();
    }

    /**
     *  Get user by id from DB and map it to UserDetailsDto object
     * @param id user id
     * @return UserDetailsDto object
     */
    @GetMapping("/{id}")
    public UserDetailsDto getUser(@PathVariable Long id){
        return userService.getUser(id).map(userMapper::userDetailsDto).orElse(null);

    }

    /**
     *  Create new user and save it in DB
     * @param userDto UserDto object
     * @return User object
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public User addUser(@RequestBody UserDto userDto){
        return userService.createUser(userMapper.toEntitySave(userDto));
    }

    /**
     *  Delete user from DB by id
     * @param userId user id
     * @return id of deleted user, status 204
     */
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{userId}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(userId,NO_CONTENT);
    }

    /**
     *  Get user by email from DB and map it to UserEmailDto object
     * @param email user email
     * @return UserEmailDto object
     */
    @GetMapping("/email")
    public List<UserEmailDto> getUserByEmail(@RequestParam String email){
        return userService.getUserByEmail(email)
                .stream()
                .map(userMapper::userEmailDto)
                .toList();
    }

    /**
     *  Update user in DB by id and map it to User object
     * @param userId
     * @param changedUser
     * @return User object
     */
    @ResponseStatus(ACCEPTED)
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody UserDto changedUser) {
        return new ResponseEntity<>(userService.updateUser(userId, userMapper.toEntitySave(changedUser)), ACCEPTED);
    }


    /**
     *  Get all users who were created after the given date
     * @param date the date after which the users were created
     * @return List of UserDto objects
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUserOlderThan(@PathVariable("time") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return userService.findUserOlderThen(date)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}