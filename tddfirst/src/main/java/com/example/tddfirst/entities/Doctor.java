package com.example.tddfirst.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Banjoman
 *
 */
@Getter
@Setter
public class Doctor {
    @Id
    private String id;

    private String firstName;

    private String surName;

    @DBRef
    private List<Patient> patients;

    public Doctor(String firstName, String surName) {								// viene inizializzato l'oggetto "Doctor" con questa chiamata
        this.firstName = firstName;
        this.surName = surName;
		patients = new ArrayList<Patient>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }
    
    public List<Patient> getPatients() {
        return patients;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void insertPatient(Patient patient) {
		this.patients.add(patient);
    }

    public void removePatient(String patientId) {

		this.patients.removeIf(patientInList -> patientId.equals(patientInList.getId()));
	}

    @Override
    public String toString() {
        return String.format("Doctor[id=%s, firstName='%s', surName='%s']", id, firstName, surName);

    }

	public boolean checkPatient(String surnamePatient) {
		for (Patient patient : patients) {
			if (patient.getSurName().equalsIgnoreCase(surnamePatient)) {
				return true;
			}
		}
		return false; 
		
	}
}
