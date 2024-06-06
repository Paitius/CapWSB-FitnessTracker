package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    @Lazy
    private final TrainingServiceImpl trainingService;

    private final TrainingMapper trainingMapper;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable long userId) {
        return trainingService.getAllTrainingsForDedicatedUser(userId).stream().map(trainingMapper::toDto).toList();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addTraining")
    public Training addTraining(@RequestBody TrainingDto trainingDto) {
        return trainingService.createTraining(trainingMapper.toEntity(trainingDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/finishedTrainings")
    public List<TrainingDto> getFinishedTrainings() {
        return trainingService.findAllFinishedTrainings().stream().map(trainingMapper::toDto).toList();
    }

    @GetMapping("/finishedTrainings/beforeDate/{date}")
    public List<TrainingDto> getFinishedTrainingsBefore(@PathVariable Date date) {
        return trainingService.findAllFinishedTrainingsBefore(date).stream().map(trainingMapper::toDto).toList();
    }

   @GetMapping("/byActivity")
   public List<TrainingDto> getTrainingsByActivity(@RequestParam ActivityType activityType) {
        // ActivityType.RUNNING;
        return trainingService.findALlTrainingsByActivity(activityType).stream().map(trainingMapper::toDto).toList();
    }

    // TODO: fix error
    @PatchMapping("/update/byAverageSpeed/{id}")
    public void updateTraining(@PathVariable Long id, @RequestParam double averageSpeed ) {
        Optional<Training> optionalTraining = trainingService.getTraining(id);
        if (optionalTraining.isPresent()) {
            Training training = optionalTraining.get();
            training.setAverageSpeed(averageSpeed);
            trainingService.updateTraining(training);
        }
        else { throw new TrainingNotFoundException(id); }
    }

}