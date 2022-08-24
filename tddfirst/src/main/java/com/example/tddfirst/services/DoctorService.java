package com.example.tddfirst.services;

import java.util.List;
import java.util.Optional;

import com.example.tddfirst.entities.Clinic;
import com.example.tddfirst.entities.Doctor;

public interface DoctorService {


	Doctor findBySurName(String surName); //

	List<Doctor> findByFirstName(String firstName); //

	void save(Doctor doctor);

	void delete(Doctor doctor);
	
	void deleteAll();
	
	Optional<Doctor> findById(String id);


}
