package com.example.tddfirst.controller;

import com.example.tddfirst.entities.Patient;
import com.example.tddfirst.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PatientWebController {

	private static final String PATIENT_ATTRIBUTE = "patient";

	private static final String MESSAGE = "message";

	@Autowired
	private PatientService patientService;

	@GetMapping("/")
	public String index(Model model) {
		List<Patient> patients = patientService.findAll();
		model.addAttribute("patients", patients);
		model.addAttribute(MESSAGE, patients.isEmpty() ? "No patients found" : "");
		return "index";
	}

	@GetMapping("/edit_patient/{name}")
	public String editPatient(@PathVariable String name, Model model) {
		Patient patientByName = patientService.findByFirstName(name);
		model.addAttribute(PATIENT_ATTRIBUTE, patientByName);
		model.addAttribute(MESSAGE, patientByName == null ? "No patient found with name: " + name : "");
		return "edit_patient";
	}

	@GetMapping("/new_patient")
	public String newPatient(Model model) {
		model.addAttribute(PATIENT_ATTRIBUTE, new Patient("", new ArrayList<>()));
		model.addAttribute(MESSAGE, "");
		return "edit_patient";
	}

	@PostMapping("/save_patient")
	public String savePatient(Patient patientEntity, Model model) {
		final Optional<Patient> patientPresent = patientService.findById(patientEntity.getId());
		if (patientPresent.isEmpty()) {
			Patient patient = new Patient(patientEntity.getFirstName(), new ArrayList<>());
			model.addAttribute(PATIENT_ATTRIBUTE,patient);
			model.addAttribute(MESSAGE, "Patient saved.");
			patientService.save(patient);
		} else {
			patientPresent.get().setFirstName(patientEntity.getFirstName());
			model.addAttribute(PATIENT_ATTRIBUTE,patientPresent);
			patientService.save(patientPresent.get());
			model.addAttribute(MESSAGE, "Patient updated.");
		}
		return "redirect:/";
	}
	

	@RequestMapping(value = "/delete_patient/{name}", method = { RequestMethod.GET,
			RequestMethod.DELETE })
	public String deletePatients(@PathVariable String name, Model model) {
		Patient patient = patientService.findByFirstName(name);
		model.addAttribute(PATIENT_ATTRIBUTE, name);
		patientService.delete(patient);
		return "redirect:/";
	}

}
