package com.example.tddfirst.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tddfirst.entities.Doctor;
import com.example.tddfirst.entities.Patient;
import com.example.tddfirst.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	DoctorRepository doctorrepository;

	@Override
	public Doctor findBySurName(String surName) {
		return doctorrepository.findBySurName(surName);
	}

	@Override
	public List<Doctor> findByFirstName(String firstName) {
		return doctorrepository.findByFirstName(firstName);
		}

	@Override
	public Optional<Doctor> findById(String id){return doctorrepository.findById(id);};

	@Override
	public void save(Doctor doctor) {
		doctorrepository.save(doctor);
	}

	@Override
	public void delete(Doctor doctor) {
		doctorrepository.delete(doctor);
		
	}

	@Override
	public void deleteAll() {
		doctorrepository.deleteAll();
		
	}
	
	public Doctor insertPatient(Doctor doctor, Patient patient) {
		doctor.insertPatient(patient);
		doctorrepository.save(doctor);
		return (Doctor) this.findByFirstName(doctor.getFirstName());
	}


}
