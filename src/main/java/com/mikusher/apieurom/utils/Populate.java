package com.mikusher.apieurom.utils;

import com.mikusher.apieurom.models.Results;
import com.mikusher.apieurom.repository.ResultsRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Populate {


    private static final Logger log = LoggerFactory.getLogger(Populate.class);

    public static List<String> getDaysRangeByDaysOfWeek(LocalDate startDate, LocalDate endDate, DayOfWeek dayOfWeek1, DayOfWeek dayOfWeek2) {

        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        return IntStream.iterate(1, i -> i + 1)
                .limit(numOfDaysBetween)
                .limit(numOfDaysBetween)
                .mapToObj(startDate::plusDays).filter(e -> e.getDayOfWeek().equals(dayOfWeek1) || e.getDayOfWeek().equals(dayOfWeek2))
                .map(y -> y.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .collect(Collectors.toList());
    }

    public static List<Results> getAllRemoteResults(List<String> drawDays) throws Exception{

        ResultsRepository resultsRepository = null;

        List<Results> euroResults = new ArrayList<>();

        Document doc = null;
        for (String day : drawDays) {
            Results euroResult = new Results();
            String url = "https://www.euro-millions.com/results/"+day;
            //get DOM data
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com").ignoreHttpErrors(true)
                    .get();

            if(doc.title().contains("404") || doc.title().trim().isEmpty()){
                continue;
            }

            //extract the draw list
            Element drawsballs = doc.getElementById("ballsAscending");

            //get the draw balls
            euroResult.setBallsDrawn(drawsballs.getElementsByClass("ball").stream().map(Element::text).collect(Collectors.toList()));

            //get the draw date
            euroResult.setDate(day);

            //get the 3 licky stars
            euroResult.setLuckyStars(drawsballs.getElementsByClass("lucky-star").stream().map(Element::text).collect(Collectors.toList()));

            euroResults.add(euroResult);

        }

        log.info("Results successfully extracted");
        return euroResults;

    }

}
