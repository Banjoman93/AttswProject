package com.example.tddfirst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.tddfirst.entities.Patient;
import com.example.tddfirst.repository.PatientsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class TddfirstApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(TddfirstApplication.class);
	
	@Autowired
	private PatientsRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(TddfirstApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Start Program");
		repository.deleteAll();
		repository.save(new Patient("Orazio", "Picentini", 5));
		repository.save(new Patient("Alberto", "Fumagalli", 2));
		repository.save(new Patient("Mauro", "Smusi", 3));
		repository.save(new Patient("Giorgio", "Picci", 12));

		// fetch all patients
		logger.info("Patients found with findAll():");
		logger.info("-------------------------------");
		String patientString = null;
		for (Patient patient : repository.findAll()) {
			patientString=patient.toString();
			logger.info("{}", patientString);
		}
		logger.info("End Program");

	}
}
