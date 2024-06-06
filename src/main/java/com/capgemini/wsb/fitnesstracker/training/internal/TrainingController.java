package com.capgemini.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingDto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {
    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    @GetMapping
    public List<TrainingDto> getAllTraining(){
        return trainingService.getAllTraining().stream().map(trainingMapper::toDto).toList();
    }

    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingForUser(@PathVariable Long userId){
        return trainingService.getTrainingForUser(userId).stream().map(trainingMapper::toDto).toList();
    }

    @GetMapping("/finishedTrainings/beforeDate/{date}")
    public List<TrainingDto> getFinishedTrainingAfterDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return  trainingService.getFinishedTrainingAfterDate(date).stream().map(trainingMapper::toDto).toList();
    }

    @GetMapping("/activityType")
    public List<TrainingDto> getAllTreningsForActivityType(@RequestParam String activityType){
        return trainingService.getAllTreningForActivityType(ActivityType.valueOf(activityType)).stream().map(trainingMapper::toDto).collect(Collectors.toList());
    }

}