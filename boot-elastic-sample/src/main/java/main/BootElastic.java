package main;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import main.model.Incident;
import main.service.IncidentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class BootElastic implements CommandLineRunner {

	@Autowired
	private IncidentService incidentService;

	private static final Logger logger = LoggerFactory.getLogger(BootElastic.class);

	private void addSomeMovies() throws Exception {
		List<Incident> incidents = readIncidents();
		incidentService.addIncidents(incidents);
		;

	}

	/**
	 * Reads a file {@code starbucks.csv} from the class path and parses it into
	 * {@link Store} instances about to persisted.
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<Incident> readIncidents() throws Exception {

		ClassPathResource resource = new ClassPathResource("SFPD_Incidents_-_from_1_January_2003-4.csv");
		Scanner scanner = new Scanner(resource.getInputStream());
		String line = scanner.nextLine();
		scanner.close();

		FlatFileItemReader<Incident> itemReader = new FlatFileItemReader<Incident>();
		itemReader.setResource(resource);

		// DelimitedLineTokenizer defaults to comma as its delimiter
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames(line.split(","));
		tokenizer.setStrict(false);

		DefaultLineMapper<Incident> lineMapper = new DefaultLineMapper<Incident>();
		lineMapper.setFieldSetMapper(fields -> {

			Incident incident = new Incident();

			incident.setAddress(fields.readString("Address"));
			incident.setCategory(fields.readString("Category"));
			incident.setDate(fields.readString("Date"));
			incident.setDayofweek(fields.readString("DayOfWeek"));
			incident.setDescript(fields.readString("Descript"));
			incident.setIncidntnum(fields.readString("IncidntNum"));
			incident.setLocation(fields.readString	("Location"));
			incident.setPddistrict(fields.readString("PdDistrict"));
			incident.setPdid(fields.readString("PdId"));
			incident.setResolution(fields.readString("Resolution"));
			incident.setTime(fields.readString("Time"));
			incident.setX(fields.readString("X"));
			incident.setY(fields.readString("Y"));

			return incident;
		});

		lineMapper.setLineTokenizer(tokenizer);
		itemReader.setLineMapper(lineMapper);
		itemReader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());
		itemReader.setLinesToSkip(1);
		itemReader.open(new ExecutionContext());

		List<Incident> incidents = new ArrayList();
		Incident incident = null;

		do {

			incident = itemReader.read();

			if (incident != null) {
				incidents.add(incident);
			}

		} while (incident != null);

		return incidents;
	}

	public void run(String... args) throws Exception {
		addSomeMovies();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BootElastic.class, args);
	}
}
