package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {


    @Query("select training from Training training where training.user.id = :userID")
    List<Training> findAllTrainingsByUserID(@Param("userID") long userID);

    @Query("select training from Training training where training.endTime < :today")
    List<Training> findAllFinishedTrainings(@Param("today") LocalDate today);

    @Query("select training from Training training where training.endTime < :date")
    List<Training> findAllFinishedTrainingsBefore(@Param("date") Date date);

    @Query("select training from Training training where training.activityType = :activity")
    List<Training> findAllTrainingsByActivity(@Param("activity") ActivityType activity);



}