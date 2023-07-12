package com.example.tddfirst.services;

import java.util.List;
import java.util.Optional;

import com.example.tddfirst.entities.Doctor;
import com.example.tddfirst.entities.Patient;

public interface DoctorService {


	Doctor findBySurName(String surName); //

	
	List<Doctor> findByFirstName(String firstName); //

	void save(Doctor doctor);

	void delete(Doctor doctor);
	
	void deleteAll();
	
	Doctor insertPatient(Doctor doctor, Patient patient);
	
	Optional<Doctor> findById(String id);

}
