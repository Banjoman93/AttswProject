package com.example.tddfirst.services;

import java.util.List;
import java.util.Optional;

import com.example.tddfirst.entities.Patient;

public interface PatientService {


	Patient findBySurName(String surName); //

	List<Patient> findByFirstName(String firstName); //

	void save(Patient patient);

	void delete(Patient patient);
	
	void deleteAll();
	
	Patient insertPatient(Patient patient);
	
	Optional<Patient> findById(String id);


}
