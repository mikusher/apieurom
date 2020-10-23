package com.mikusher.apieurom.scheduling;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.mikusher.apieurom.models.Results;
import com.mikusher.apieurom.repository.ResultsRepository;
import com.mikusher.apieurom.utils.Populate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    @Autowired
    private ResultsRepository resultsRepository;


    /**
     * Update database automatically ever Mon and Fri
     */
    //@Scheduled(cron="0 0 21 ? * TUE,FRI *", zone="Europe/Lisbon")
    public void updateResultTable() {
        log.info("Method executed at every Tuesday and Friday at 9PM. Current time is :: "+ dateFormat.format(new Date()));

        DateTimeFormatter scheduledFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String lastDay = String.format( "%s-%s-%s" , LocalDate.now().getDayOfMonth() - 1, LocalDate.now().getMonthValue(), LocalDate.now().getYear());

        List<String> todayLimite = Populate.getDaysRangeByDaysOfWeek(LocalDate.parse(lastDay, scheduledFormat), LocalDate.now(), DayOfWeek.TUESDAY, DayOfWeek.FRIDAY);

        List<Results> allResults = null;
        try {
            allResults = Populate.getAllRemoteResults(todayLimite);
            for (Results result : allResults){
                resultsRepository.save(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
