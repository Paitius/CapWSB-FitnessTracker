package com.capgemini.wsb.fitnesstracker.statistics.internal;



import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import io.micrometer.core.instrument.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

interface StatisticRepository extends JpaRepository<Statistics, Long> {
    default List<Statistics> getStatisticsForUser(Long statisticsId){
        return findAll().stream().filter(statistic -> statistic.getUser().getId().equals(statisticsId)).toList();
    }

    default List<Statistics> findByBurnedCalories(int calories){
        return findAll().stream().filter(statistic -> statistic.getTotalCaloriesBurned() > calories).toList();
    };
}