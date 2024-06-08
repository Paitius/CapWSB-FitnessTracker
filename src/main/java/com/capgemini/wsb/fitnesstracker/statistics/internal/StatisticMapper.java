package com.capgemini.wsb.fitnesstracker.statistics.internal;


import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatisticMapper {

    final UserMapper userMapper;

    StatisticsDto toDto(Statistics statistics){
        return new StatisticsDto(statistics.getId(),
                userMapper.userTrainingDto(statistics.getUser()),
                statistics.getTotalTrainings(),
                statistics.getTotalDistance(),
                statistics.getTotalCaloriesBurned());
    }

    Statistics toEntity(StatisticsDto statisticsDto){
        return new Statistics(
                statisticsDto.id(),
                statisticsDto.user(),
                statisticsDto.totalTrainings(),
                statisticsDto.totalDistance(),
                statisticsDto.totalCaloriesBurned());
    }
}
