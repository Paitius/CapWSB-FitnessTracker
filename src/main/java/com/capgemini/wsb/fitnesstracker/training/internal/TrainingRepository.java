package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     *  Returns all trainings.
     * @param userId
     * @return List of all trainings
     */
    default List<Training> findByUserId(Long userId){
        return findAll().stream().filter(training -> Objects.equals(training.getUser().getId(), userId))
                .toList();
    }

    /**
     * Returns all trainings after a given date.
     * @param date
     * @return List of all trainings
     */
    default List<Training> findTraningAfterDate(Date date){
        return findAll().stream().filter(training -> training.getEndTime().after(date)).collect(Collectors.toList());
    }

    /**
     *  Returns all trainings for a given activity type.
     * @param activityType
     * @return List of all trainings
     */
    default List<Training> findTraningsForActivityType(ActivityType activityType){
        return findAll().stream()
                .filter(training -> Objects.equals(training.getActivityType(), activityType))
                .toList();
    }


}