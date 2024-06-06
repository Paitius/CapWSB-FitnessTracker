package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;

    /**
     *  Returns a training by its ID.
     * @param trainingId id of the training to be searched
     * @return training
     */
    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    /**
     *  Returns all trainings.
     * @return List of all trainings
     */
    @Override
    public List<Training> getAllTraining() {
        return trainingRepository.findAll();
    }

    /**
     *  Returns all trainings for a user.
     * @param userId
     * @return List of all trainings
     */
    @Override
    public List<Training> getTrainingForUser(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    /**
     *  Returns all trainings after a given date.
     * @param date  date after which the trainings were created
     * @return List of all trainings
     */
    @Override
    public List<Training> getFinishedTrainingAfterDate(Date date){
        return trainingRepository.findTraningAfterDate(date);
    }

    /**
     *  Returns all trainings for a given activity type.
     * @param activityType type of the activity
     * @return List of all trainings
     */
    public List<Training> getAllTreningForActivityType(ActivityType activityType) {
        return trainingRepository.findTraningsForActivityType(activityType);
    }

    /**
     *  Creates a new training.
     * @param training training to be created
     * @return created training
     */
    @Override
    public Training createTraining(Training training) {
        log.info("Creating Training {}", training);
        return trainingRepository.save(training);
    }

    /**
     *  Updates an existing training.
     * @param id
     * @param training
     * @return   updated training
     */
    @Override
    public Training updateTraining(Long id, Training training) {
        return trainingRepository.save(training);
    }   
}