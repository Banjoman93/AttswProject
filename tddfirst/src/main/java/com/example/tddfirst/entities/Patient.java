/**
 * 
 */
package com.example.tddfirst.entities;

import org.springframework.data.annotation.Id;

/**
 * @author Banjoman
 *
 */

public class Patient {
	@Id
	public String id;

	public String firstName;

	public String surName;

	public int vacancyDays;

	public Patient(String firstName, String surName, int vacancyDays) {
		this.firstName = firstName;
		this.surName = surName;
		this.vacancyDays = vacancyDays;
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

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public int getVacancyDays() {
		return vacancyDays;
	}

	public void setVacancyDays(int vacancyDays) {
		this.vacancyDays = vacancyDays;
	}

	@Override
	public String toString() {
		return String.format("Patient[id=%s, firstName='%s', surName='%s']", id, firstName, surName);

	}
}
