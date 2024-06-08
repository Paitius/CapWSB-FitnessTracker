package com.capgemini.wsb.fitnesstracker.statistics.internal;


import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.user.internal.UserServiceImpl;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


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
}
