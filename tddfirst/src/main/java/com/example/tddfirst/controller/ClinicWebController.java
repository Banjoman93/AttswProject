package com.example.tddfirst.controller;

import com.example.tddfirst.entities.Clinic;
import com.example.tddfirst.services.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ClinicWebController {

	private static final String CLINIC_ATTRIBUTE = "clinic";

	private static final String MESSAGE = "message";

	@Autowired
	private ClinicService clinicService;

	@GetMapping("/")
	public String index(Model model) {
		List<Clinic> clinics = clinicService.findAll();
		model.addAttribute("clinics", clinics);
		model.addAttribute(MESSAGE, clinics.isEmpty() ? "No clinics found" : "");
		return "index";
	}

	@GetMapping("/edit_clinic/{name}")
	public String editClinic(@PathVariable String name, Model model) {
		Clinic clinicByName = clinicService.findByFirstName(name);
		model.addAttribute(CLINIC_ATTRIBUTE, clinicByName);
		model.addAttribute(MESSAGE, clinicByName == null ? "No clinic found with name: " + name : "");
		return "edit_clinic";
	}

	@GetMapping("/new_clinic")
	public String newClinic(Model model) {
		model.addAttribute(CLINIC_ATTRIBUTE, new Clinic("", new ArrayList<>()));
		model.addAttribute(MESSAGE, "");
		return "edit_clinic";
	}

	@PostMapping("/save_clinic")
	public String saveClinic(Clinic clinicEntity, Model model) {
		final Optional<Clinic> clinicPresent = clinicService.findById(clinicEntity.getId());
		if (clinicPresent.isEmpty()) {
			Clinic clinic = new Clinic(clinicEntity.getFirstName(), new ArrayList<>());
			model.addAttribute(CLINIC_ATTRIBUTE,clinic);
			model.addAttribute(MESSAGE, "Clinic saved.");
			clinicService.save(clinic);
		} else {
			clinicPresent.get().setFirstName(clinicEntity.getFirstName());
			model.addAttribute(CLINIC_ATTRIBUTE,clinicPresent);
			clinicService.save(clinicPresent.get());
			model.addAttribute(MESSAGE, "Clinic updated.");
		}
		return "redirect:/";
	}
	

	@RequestMapping(value = "/delete_clinic/{name}", method = { RequestMethod.GET,
			RequestMethod.DELETE })
	public String deleteClinics(@PathVariable String name, Model model) {
		Clinic clinic = clinicService.findByFirstName(name);
		model.addAttribute(CLINIC_ATTRIBUTE, name);
		clinicService.delete(clinic);
		return "redirect:/";
	}

}
