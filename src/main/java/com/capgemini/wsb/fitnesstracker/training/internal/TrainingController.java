package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {
    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;
    private final UserServiceImpl userServiceImpl;

    @GetMapping
    public List<TrainingDto> getAllTraining() {
        return trainingService.getAllTraining().stream().map(trainingMapper::toDto).toList();
    }

    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingForUser(@PathVariable Long userId) {
        return trainingService.getTrainingForUser(userId).stream().map(trainingMapper::toDto).toList();
    }

    @GetMapping("/finishedTrainings/beforeDate/{date}")
    public List<TrainingDto> getFinishedTrainingAfterDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return trainingService.getFinishedTrainingAfterDate(date).stream().map(trainingMapper::toDto).toList();
    }

    @GetMapping("/activityType")
    public List<TrainingDto> getAllTreningsForActivityType(@RequestParam String activityType) {
        return trainingService.getAllTreningForActivityType(ActivityType.valueOf(activityType)).stream().map(trainingMapper::toDto).collect(Collectors.toList());
    }
    //TODO failed test

    @PostMapping("/addTraining")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto addTraining(@RequestBody TrainingAddDto trainingAddDto) {
        Long userId = trainingAddDto.userId();
        User user =userServiceImpl.getUser(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Training training = trainingService.createTraining(trainingMapper.toEntity(trainingAddDto, user, null));
        return trainingMapper.toDto(training);
    }

    @PutMapping("/{trainingId}")
    @ResponseStatus(HttpStatus.OK)
    public TrainingDto updateTraining(@PathVariable Long trainingId, @RequestBody TrainingAddDto trainingAddDto) {
        Training originalTraining = trainingService.getTraining(trainingId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Training training = trainingService.updateTraining(trainingId, trainingMapper.toEntity(trainingAddDto, originalTraining.getUser(), trainingId));
        return trainingMapper.toDto(training);
    }

}
