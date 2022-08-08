package com.example.tddfirst;

import com.example.tddfirst.entities.Doctor;
import com.example.tddfirst.entities.Patient;
import com.example.tddfirst.services.DoctorService;
import com.example.tddfirst.services.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

	
@SpringBootTest
class TddfirstApplicationDoctorTest {
    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private PatientService patientService;

    @BeforeEach			
    //annotazione che dice al framework spring di eseguire ogni volta che deve eseguire un test di unità "prima di eseguire questo test esegui contentload"
    void contextLoads() {
    	doctorService.deleteAll();
        patientService.deleteAll();
    }

    @Test
    void DoctorInsert() {
        doctorService.save(new Doctor("Gaia", "Pittella"));
        assertEquals("Gaia", doctorService.findBySurName("Pittella").getFirstName());
    }
    
    @Test
    void DoctorsInsert() {
        doctorService.save(new Doctor("Ugo", "Ferrari"));
        doctorService.save(new Doctor("Ugo", "Pittella"));
        assertEquals(2, doctorService.findByFirstName("Ugo").size());
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
    void DoctorModify2() {
        doctorService.save(new Doctor("Gaia", "Pittella"));
        Doctor doctor = doctorService.findBySurName("Pittella");
        doctor.setSurName("Boscolo");
        doctorService.save(doctor);
        doctor = doctorService.findBySurName("Boscolo");
        assertEquals(doctor.getSurName(), "Boscolo");
        assertTrue(doctor.toString().contains("firstName='Gaia', surName='Boscolo'"));
    }

    @Test
    void DoctorDelete() {
        doctorService.save(new Doctor("Gaia", "Pittella"));
        Doctor doctor = doctorService.findBySurName("Pittella");
        doctorService.delete(doctor);
        assertNull(doctorService.findBySurName("Pittella"));							//il test sarà corretto solo se passerà l'assertNull (non ci dovrà essere nessun dottore all'interno del repository con il cognome richiesto)
    }
    
    @Test
    void removePatient() {
        doctorService.save(new Doctor("Gaia", "Pittella"));
        Doctor doctor = doctorService.findBySurName("Pittella");

        Patient patient = new Patient("Ugo", "Sghella", 1);
        patientService.save(patient);
        doctor.insertPatient(patient);

        List<Patient> listPatient = doctor.getPatients();
        Patient patientToRomove = null;

        for (Patient patient : listPatient) {
            if (patient.getSurName().equals("Sghella")) {
                patientToRomove = patient;
            }
        }

        if (patientToRomove != null) {
            doctor.removePatient(patientToRomove.getId());
        }

        assertFalse(doctor.checkPatient("Sghella"));
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
        assertFalse(doctor.checkPatient("Lavalle"));
       
    }
   

}
