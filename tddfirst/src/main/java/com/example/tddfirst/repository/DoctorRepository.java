/**
 * Firma di metodo Patient
 */
package com.example.tddfirst.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.tddfirst.entities.Doctor;

/**
 * @author Banjoman
 *
 */
public interface DoctorRepository extends MongoRepository<Doctor, String> {
	Doctor findBySurName(String surName);// Ritorna il dottore dal cognome
	List <Doctor> findByFirstName(String firstName); // Ritorna lista dei dottori dal nome
}
