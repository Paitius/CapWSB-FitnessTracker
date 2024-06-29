package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticService;
import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsProvider;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@NonNullByDefault
public class StatisticServiceImpl implements StatisticService, StatisticsProvider{

    private final StatisticRepository statisticRepository;

    @Override
    public Statistics createStatistic(final Statistics statistics) {
        return statisticRepository.save(statistics);
    }

    @Override
    public void deleteStatistics(Long id) {
        statisticRepository.deleteById(id);
    }

    @Override
    public List<Statistics> getStatistics(final Long statisticsId) {
        return statisticRepository.getStatisticsForUser(statisticsId);
    }

    @Override
    public List<Statistics> findStatisticsByCaloriesGreaterThan(int calories) {
        return statisticRepository.findByBurnedCalories(calories);
    }


}
