/**
 *
 */
package com.example.tddfirst.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * @author Banjoman
 *
 */

public class Clinic {
    @Id
    public String id;

    private String firstName;

    @DBRef
    private final List<Doctor> doctor;

    public Clinic(String firstName, List<Doctor> doctor) {								// viene inizializzato l'oggetto "Clinic" con questa chiamata
        this.firstName = firstName;
		this.doctor = doctor;
		}

    public String getId() {
        return id;
        
    }

    public List<Doctor> getDoctor() {
		return doctor;
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

    public void insertDoctor(Doctor doctor) {
		this.setDoctor(doctor);
    }
   
    //@Transient
    public void setDoctor(Doctor doctor) {
		this.doctor.add(doctor);
    }

    public void removeDoctor(String doctorId) {

		this.doctor.removeIf(doctorInList -> doctorId.equals(doctorInList.getId()));
	}

    @Override
    public String toString() {
        return String.format("Clinic[id=%s, firstName='%s']", id, firstName);

    }
}
