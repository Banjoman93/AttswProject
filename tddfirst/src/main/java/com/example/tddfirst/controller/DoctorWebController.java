package com.example.tddfirst.controller;

import com.example.tddfirst.entities.Doctor;
import com.example.tddfirst.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class DoctorWebController {

	private static final String DOCTOR_ATTRIBUTE = "doctor";

	private static final String MESSAGE = "message";

	@Autowired
	private DoctorService doctorService;

	@GetMapping("/")
	public String index(Model model) {
		List<Doctor> doctors = doctorService.findByFirstName(DOCTOR_ATTRIBUTE);
		model.addAttribute("doctors", doctors);
		model.addAttribute(MESSAGE, doctors.isEmpty() ? "No doctors found" : "");
		return "index";
	}

	@GetMapping("/edit_doctor/{name}")
	public String editDoctor(@PathVariable String name, Model model) {
		List <Doctor> doctorByName = doctorService.findByFirstName(name);
		model.addAttribute(DOCTOR_ATTRIBUTE, doctorByName);
		model.addAttribute(MESSAGE, doctorByName == null ? "No doctor found with name: " + name : "");
		return "edit_doctor";
	}

	@GetMapping("/new_doctor")
	public String newDoctor(Model model) {
		model.addAttribute(DOCTOR_ATTRIBUTE, new Doctor("", new ArrayList<>()));
		model.addAttribute(MESSAGE, "");
		return "edit_doctor";
	}

	@PostMapping("/save_doctor")
	public String saveDoctor(Doctor doctorEntity, Model model) {
		final Optional <Doctor> doctorPresent = doctorService.findBySurName(doctorEntity.getSurName());
		if (doctorPresent.isEmpty()) {
			Doctor doctor = new Doctor(doctorEntity.getFirstName(), new ArrayList<>());
			model.addAttribute(DOCTOR_ATTRIBUTE,doctor);
			model.addAttribute(MESSAGE, "Doctor saved.");
			doctorService.save(doctor);
		} else {
			doctorPresent.get().setFirstName(doctorEntity.getFirstName());
			model.addAttribute(DOCTOR_ATTRIBUTE,doctorPresent);
			doctorService.save(doctorPresent.get());
			model.addAttribute(MESSAGE, "Doctor updated.");
		}
		return "redirect:/";
	}
	

	@RequestMapping(value = "/delete_doctor/{name}", method = { RequestMethod.GET,
			RequestMethod.DELETE })
	public String deleteDoctors(@PathVariable String name, Model model) {
		List<Doctor> doctor = doctorService.findByFirstName(name);
		model.addAttribute(DOCTOR_ATTRIBUTE, name);
		return "redirect:/";
	}

}
