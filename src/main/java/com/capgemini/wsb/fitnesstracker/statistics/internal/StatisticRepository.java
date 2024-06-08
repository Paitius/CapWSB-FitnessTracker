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
}