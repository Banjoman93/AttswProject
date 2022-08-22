package com.example.tddfirst.services;

import com.example.tddfirst.entities.Clinic;
import com.example.tddfirst.entities.Doctor;

import java.util.List;
import java.util.Optional;

public interface ClinicService {

	List<Clinic> findAll();

	Clinic findByFirstName(String firstName); //

	void save(Clinic clinic);

	void delete(Clinic clinic);
	
	Clinic insertDoctor(Clinic clinic, Doctor doctor);

	Optional<Clinic> findById(String id);
}
