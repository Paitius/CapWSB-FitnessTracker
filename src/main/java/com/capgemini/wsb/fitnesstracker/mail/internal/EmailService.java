package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailProvider;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.DAY_OF_MONTH;

@Service
@Slf4j
class EmailService implements EmailProvider {


    @Override
    public EmailDto createEmail(String recipient, String title, List<Training> trainings) {
        final Calendar lastWeek = Calendar.getInstance();
        lastWeek.add(DAY_OF_MONTH, -7);
        final Date lastWeekDate = lastWeek.getTime();
        final Date now = Calendar.getInstance().getTime();


        final List<Training> finishedTrainings = trainings
                .stream()
                .filter(training -> training.getEndTime().after(lastWeekDate) && training.getEndTime().before(now))
                .toList();

        final StringBuilder builder = new StringBuilder("""
                You had %s trainings last week
                """.formatted(finishedTrainings.size()
        ));
        finishedTrainings.forEach(training -> {
            builder.append("""
                training start: %s
                training end: %s
                activity type: %s
                distance: %s
                average speed: %s
                ----
                """.formatted(training.getStartTime(),
                    training.getEndTime() == null ? "-" : training.getEndTime(),
                    training.getActivityType(),
                    training.getDistance(),
                    training.getAverageSpeed()
            ));
        });

        EmailDto emailDto = new EmailDto( recipient, title, builder.toString());
        return emailDto;
    }

}