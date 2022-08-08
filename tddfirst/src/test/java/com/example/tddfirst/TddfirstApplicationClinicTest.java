package com.example.tddfirst;

import com.example.tddfirst.entities.Clinic;
import com.example.tddfirst.entities.Doctor;
import com.example.tddfirst.repository.ClinicRepository;
import com.example.tddfirst.services.ClinicService;
import com.example.tddfirst.services.DoctorService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TddfirstApplicationClinicTest {
    @Autowired
    private ClinicRepository clinicServiceRepository;
    @Autowired
    private ClinicService clinicServiceServiceImpl;
    @Autowired
    private DoctorService doctorService;

    @BeforeEach
    //annotazione che dice al framework spring di eseguire ogni volta che deve eseguire un test di unità "prima di eseguire questo test esegui contentload"
    @AfterEach
        // in questo modo ogni test è atomico e non sporchi il DB
    void contextLoads() {
        clinicServiceRepository.deleteAll();
        doctorService.deleteAll();
    }

    @Test
    void ClinicInsert() {
        clinicServiceServiceImpl.save(new Clinic("Radiology", Collections.<Doctor>emptyList()));
        assertEquals("Radiology", clinicServiceServiceImpl.findByFirstName("Radiology").getFirstName());
    }
    

    @Test
    void insertDoctor() {
        clinicServiceServiceImpl.save(new Clinic("Radiology", Collections.<Doctor>emptyList()));
        Clinic clinic = clinicServiceServiceImpl.findByFirstName("Radiology");
        Doctor doctorToInsert = new Doctor("Ugo","Sghella Curcio");
        doctorService.save(doctorToInsert);
        Doctor doctor = doctorService.findBySurName(doctorToInsert.getSurName());
        Clinic clinicToCheck = clinicServiceServiceImpl.insertDoctor(clinic, doctor);
        assertEquals(1, clinicToCheck.getDoctor().size());
        Doctor doctorToFind = clinicToCheck.getDoctor().get(0);
        assertTrue(doctorToFind.toString().contains(""));
    }
    
    @Test
    void removeDoctor() {
    	//CREAZIONE E INSERIMENTO
    	
    	clinicServiceServiceImpl.save(new Clinic("Radiology", Collections.<Doctor>emptyList()));
        Clinic clinic = clinicServiceServiceImpl.findByFirstName("Radiology");
        doctorService.save(new Doctor("Ugo", "Sghella Ciao"));
        Doctor doctor = doctorService.findBySurName("Sghella Ciao");
        clinic.insertDoctor(doctor);
        clinicServiceServiceImpl.save(clinic);
        
        //RECUPERO DOTTORE E SUA CANCELLAZIONE
        Doctor doctorToRemove = clinic.getDoctor().get(0);
        clinic.removeDoctor(doctorToRemove.getId());
        
        //ASSERT
        assertEquals(0, clinic.getDoctor().size());
        doctorService.delete(doctor);
        clinicServiceServiceImpl.delete(clinic);
        
    }


    @Test
    void ClinicModify() {
        clinicServiceServiceImpl.save(new Clinic("Radiology", Collections.<Doctor>emptyList()));
        assertEquals("Radiology", clinicServiceServiceImpl.findByFirstName("Radiology").getFirstName());
        Clinic clinic = clinicServiceServiceImpl.findByFirstName("Radiology");
        clinic.setFirstName("Cardiology");
        clinicServiceServiceImpl.save(clinic);
        assertEquals("Cardiology", clinicServiceServiceImpl.findByFirstName("Cardiology").getFirstName());
    }

    @Test
    void ClinicDelete() {
        clinicServiceServiceImpl.save(new Clinic("Radiology", Collections.<Doctor>emptyList()));
        Clinic clinic = clinicServiceServiceImpl.findByFirstName("Radiology");
        clinicServiceServiceImpl.delete(clinic);
        assertNull(clinicServiceServiceImpl.findByFirstName("Radiology"));							
        //il test sarà corretto solo se passerà l'assertNull (non ci dovrà essere nessun dottore all'interno del repository con il cognome richiesto)
    }

    @Test
    void ClinicToString() {
        clinicServiceServiceImpl.save(new Clinic("Radiology", Collections.<Doctor>emptyList()));
        Clinic clinic = clinicServiceServiceImpl.findByFirstName("Radiology");
        assertTrue(clinic.toString().contains("firstName='Radiology']"));
    }



}
