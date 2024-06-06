package com.capgemini.wsb.fitnesstracker.user.api;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;


public record UserTrainingDto(@Nullable  Long id, String firstName, String lastName, String email){

}
