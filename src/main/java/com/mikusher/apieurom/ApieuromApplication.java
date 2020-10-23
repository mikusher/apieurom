package com.mikusher.apieurom;

import com.mikusher.apieurom.models.Results;
import com.mikusher.apieurom.repository.ResultsRepository;
import com.mikusher.apieurom.utils.Populate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class ApieuromApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApieuromApplication.class, args);
	}

}



@Component
class DemoCommandLineRunner implements CommandLineRunner {



	@Autowired
	private ResultsRepository resultsRepository;

	@Override
	public void run(String... args) throws Exception {
		// initial date - 13-02-2004
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		List<String> daysDraws = Populate.getDaysRangeByDaysOfWeek(LocalDate.parse("13-02-2004", formatter), LocalDate.now(), DayOfWeek.TUESDAY, DayOfWeek.FRIDAY);

		List<Results> allResults = Populate.getAllRemoteResults(daysDraws);

		for (Results result : allResults){
			resultsRepository.save(result);
		}

	}
}