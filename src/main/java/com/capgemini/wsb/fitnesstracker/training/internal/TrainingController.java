package com.capgemini.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingDto;

import org.springframework.web.bind.annotation.*;

import java.util.List;


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

}