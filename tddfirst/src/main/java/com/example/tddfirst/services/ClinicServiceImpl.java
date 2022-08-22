package com.example.tddfirst.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tddfirst.entities.Clinic;
import com.example.tddfirst.entities.Doctor;
import com.example.tddfirst.repository.ClinicRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicServiceImpl implements ClinicService {
	@Autowired
	ClinicRepository clinicrepository;

	@Override
	public List<Clinic> findAll() {
		return clinicrepository.findAll();
		}

	@Override
	public Optional<Clinic> findById(String id){return clinicrepository.findById(id);};

	@Override
	public Clinic findByFirstName(String firstName) {
		return clinicrepository.findByFirstName(firstName);
		}

	@Override
	public void save(Clinic clinic) {
		clinicrepository.save(clinic);
	}

	@Override
	public void delete(Clinic clinic) {
		clinicrepository.delete(clinic);
	}

	@Override
	public Clinic insertDoctor(Clinic clinic, Doctor doctor) {
		clinic.insertDoctor(doctor);
		clinicrepository.save(clinic);
		return this.findByFirstName(clinic.getFirstName());
	}

}
