package com.example.tddfirst.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tddfirst.entities.Patient;
import com.example.tddfirst.repository.PatientsRepository;

@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	PatientsRepository patientrepository;

	@Override
	public PatientsRepository getRepository() {
		return patientrepository;
	}
	
	@Override
	public Patient findBySurName(String surName) {
		return patientrepository.findBySurName(surName);
	}

	@Override
	public List<Patient> findByFirstName(String firstName) {
		return patientrepository.findByFirstName(firstName);
		}
	
	@Override
	public Optional<Patient> findById(String id){return patientrepository.findById(id);};

	@Override
	public void save(Patient patient) {
		patientrepository.save(patient);
	}

	@Override
	public void delete(Patient patient) {
		patientrepository.delete(patient);
		
	}

	@Override
	public void deleteAll() {
		patientrepository.deleteAll();
		
	}
	
	public Patient insertPatient (Patient patient) {
		patientrepository.save(patient);
		return (Patient) this.findByFirstName(patient.getFirstName());
	}



}
