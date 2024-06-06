package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    /**
     * Creates a new user.
     * @param user user to be created
     * @return created user
     */
    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     *  Returns a user by its ID.
     * @param userId id of the user to be searched
     * @return user
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    /**
     *  Returns a user by its email.
     * @param email The email of the user to be searched
     * @return user
     */
    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     *  Returns all users.
     * @return List of all users
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     *  Deletes a user by its ID.
     * @param userId user ID
     */
    @Override
    public void deleteUser(final Long userId){
        userRepository.findById(userId).ifPresent(userRepository::delete);
    }

    /**
     *  Updates an existing user.
     * @param userId user ID
     * @param user user
     * @return updated user
     */
    @Override
    public User updateUser(Long userId, User user){
        user.setId(userId);
        return userRepository.save(user);
    }

    /**
     *  Query searching users by birthdate. It matches by exact match.
     * @param date the date after which the users were created
     * @return List of all users who were created after the given date
     */
    @Override
    public List<User>findUserOlderThen(LocalDate date){
        return userRepository.findAllByBirthDate(date);
    }
}