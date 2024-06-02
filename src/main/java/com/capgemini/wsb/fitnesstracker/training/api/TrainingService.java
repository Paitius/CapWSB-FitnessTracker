package com.capgemini.wsb.fitnesstracker.training.api;


import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface TrainingService {

    Training createTraining(Training training);

    Optional<Training> getTraining(long trainingId);

    List<Training> getAllTrainingsForDedicatedUser(long userId);

    List<Training> findAllFinishedTrainings();

    List<Training> findAllFinishedTrainingsBefore(Date date);

    List<Training> findALlTrainingsByActivity(ActivityType activityType);

    Training updateTraining(Training training);

    //void deleteAllTrainingsByUser(Long userId);
}