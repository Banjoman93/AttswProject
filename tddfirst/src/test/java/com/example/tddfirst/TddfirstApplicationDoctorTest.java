package com.example.tddfirst;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.tddfirst.entities.Doctor;
import com.example.tddfirst.entities.Patient;
import com.example.tddfirst.services.DoctorService;
import com.example.tddfirst.services.PatientService;
	
@SpringBootTest
class TddfirstApplicationDoctorTest {
    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private PatientService patientService;

    @BeforeEach																		//annotazione che dice al framework spring di eseguire ogni volta che deve eseguire un test di unità "prima di eseguire questo test esegui contentload"
    void contextLoads() {
    	doctorService.deleteAll();
        patientService.deleteAll();
    }

    @Test
    void DoctorInsert() {
        doctorService.save(new Doctor("Gaia", "Pittella"));
        assertEquals("Gaia", doctorService.findBySurName("Pittella").firstName);
    }

    @Test
    void DoctorModify() {
        doctorService.save(new Doctor("Gaia", "Pittella"));
        Doctor doctor = doctorService.findBySurName("Pittella");
        doctor.setFirstName("Ugo");
        doctorService.save(doctor);
        doctor = doctorService.findBySurName("Pittella");
        assertEquals(doctor.getFirstName(), "Ugo");
    }

    @Test
    void DoctorDelete() {
        doctorService.save(new Doctor("Gaia", "Pittella"));
        Doctor doctor = doctorService.findBySurName("Pittella");
        doctorService.delete(doctor);
        assertNull(doctorService.findBySurName("Pittella"));							//il test sarà corretto solo se passerà l'assertNull (non ci dovrà essere nessun dottore all'interno del repository con il cognome richiesto)
    }
    
    @Test
    void DoctorPatient() {
    	//insert doctor
    	doctorService.save(new Doctor("Gaia", "Pittella"));
        Doctor doctor = doctorService.findBySurName("Pittella");
        
        //insert patient
        patientService.save(new Patient("Orazio", "Picentini", 5));
        Patient patient = patientService.findBySurName("Picentini");
        
        //insert inside list doctor's patient list the patient
        doctor.insertPatient(patient);
        doctorService.save(doctor);
        
        doctor = doctorService.findBySurName("Pittella");
        assertTrue(doctor.checkPatient(patient.getSurName()));
       
    }
   

}
