package com.capgemini.wsb.fitnesstracker.statistics.internal;


import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.user.internal.UserServiceImpl;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class StatisticController {

    private final UserServiceImpl userService;
    private final StatisticServiceImpl statisticService;
    private final StatisticMapper statisticMapper;

    @PostMapping("/addStatistic")
    @ResponseStatus(HttpStatus.CREATED)
    public Statistics createStatistic(@RequestBody StatisticsDto statisticsDto) {
        return statisticService.createStatistic(statisticMapper.toEntity(statisticsDto));

    }

    @GetMapping("/findStatisticForUser/{id}")
    public List<StatisticsDto> findStatisticForUser(@PathVariable Long id) {
        return statisticService.getStatistics(id).stream().map(statisticMapper::toDto).toList();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deleteStatistic/{id}")
    public void deleteStatistic(@PathVariable Long id) {
        statisticService.deleteStatistics(id);
    }


    @GetMapping("/findStatisticsByCaloriesGreaterThan/{calories}")
    public List<StatisticsDto> findStatisticsByCaloriesGreaterThan(@PathVariable int calories) {
        return statisticService.findStatisticsByCaloriesGreaterThan(calories).stream().map(statisticMapper::toDto).toList();
    }




}
