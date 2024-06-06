package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;


    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
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
}