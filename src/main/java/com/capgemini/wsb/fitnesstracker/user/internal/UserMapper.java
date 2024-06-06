package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.*;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }


    User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }


    UserSimpleDto toUserSimpleDto(User user){
        return new UserSimpleDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    public UserTrainingDto userTrainingDto(User user){
        return new UserTrainingDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

    UserDetailsDto userDetailsDto(User user){
        return new UserDetailsDto(user.getFirstName(), user.getLastName(), user.getBirthdate(), user.getEmail());
    }

    public User toEntitySave(UserDto userDto) {
        return new User(
                userDto.Id(),
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

    UserEmailDto userEmailDto(User user){
        return new UserEmailDto(user.getId(), user.getEmail());
    }
}
