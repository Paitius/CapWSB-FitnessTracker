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


    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    @Override
    public List<Training> getAllTraining() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingForUser(Long userId) {
        return trainingRepository.findByUserId(userId);
    }


    @Override
    public List<Training> getFinishedTrainingAfterDate(Date date){
        return trainingRepository.findTraningAfterDate(date);
    }

    public List<Training> getAllTreningForActivityType(ActivityType activityType) {
        return trainingRepository.findTraningsForActivityType(activityType);
    }

    @Override
    public Training createTraining(Training training) {
        log.info("Creating Training {}", training);
        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(Long id, Training training) {
        return trainingRepository.save(training);
    }   
}