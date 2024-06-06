package com.capgemini.wsb.fitnesstracker.training.internal;


import com.capgemini.wsb.fitnesstracker.user.api.UserTrainingDto;
import jakarta.annotation.Nullable;
import lombok.Data;


import java.util.Date;

public record TrainingDto(@Nullable Long id, UserTrainingDto user, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed){

}
