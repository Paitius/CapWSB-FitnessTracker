package com.capgemini.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     * @param user user to be created
     * @return created user
     */
    User createUser(User user);

    /**
     * Returns a user by its ID.
     * @param userId user ID
     * @return user
     */
    void deleteUser(Long userId);


    /**
     * Updates an existing user.
     * @param userId user ID
     * @param user user
     * @return updated user
     */
    User updateUser(Long userId, User user);
}
