package com.capgemini.wsb.fitnesstracker.statistics.internal;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserTrainingDto;


import jakarta.annotation.Nullable;



public record StatisticsDto(@Nullable Long id, UserTrainingDto user, int totalTrainings, double totalDistance, int totalCaloriesBurned){
}
