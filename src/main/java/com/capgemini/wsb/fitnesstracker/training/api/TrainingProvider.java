package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<Training> getTraining(Long trainingId);

    /**
     *  Returns all trainings.
     * @return List of all trainings
     */
    List<Training> getAllTraining();

    /**
     *  Returns all trainings for a user.
     * @param userId
     * @return List of all trainings
     */
    List<Training> getTrainingForUser(Long userId);

    /**
     *  Returns all trainings after a given date.
     * @param date  date after which the trainings were created
     * @return List of all trainings
     */
    List<Training> getFinishedTrainingAfterDate(Date date);

    /**
     *  Returns all trainings for a given activity type.
     * @param activityType type of the activity
     * @return List of all trainings
     */
    List<Training> getAllTreningForActivityType(ActivityType activityType);

    List<Training> getAllFiltredTrainings(double filterdValue);
}