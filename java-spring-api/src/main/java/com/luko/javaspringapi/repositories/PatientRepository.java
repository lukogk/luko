package com.luko.javaspringapi.repositories;

import com.luko.javaspringapi.dto.PatientDto;
import com.luko.javaspringapi.models.Patient;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PatientRepository {
    private List<Patient> patients = new ArrayList<>(){{
        add(new Patient(UUID.randomUUID(),"peter","parker", LocalDate.now(),
                "111-222-3334","1234500"));
        add(new Patient(UUID.randomUUID(),"bruce","wayne", LocalDate.now(),
                "123-222-3335","2345600"));
        add(new Patient(UUID.randomUUID(),"stephen","strange", LocalDate.now(),
                "123-222-3336","5432100"));
    }};

    public List<Patient> getPatients() {
        return patients;
    }

    public void save(Patient patient) {
        patients.add(patient);
    }

    public Optional<Patient> getPatientById(UUID patientId) {
        Optional<Patient> patient = patients.stream().filter(x-> x.getId().compareTo(patientId) == 0).findFirst();
        return patient;
    }


    public Optional<Patient> updatePatient(UUID patientId, PatientDto patientDto) {
        this.patients.forEach(x -> {
            if(x.getId().compareTo(patientId) == 0){
                x.setPhoneNumber(patientDto.getPhoneNumber());
                x.setLastName(patientDto.getLastName());
            }
        });
       return getPatientById(patientId);
    }

    public void deletePatient(UUID patientId) {
        Optional<Patient> patient = patients.stream().filter(x-> x.getId().equals(patientId)).findFirst();
        if(patient.isPresent()) {
            patients.remove(patient.get());
        }
    }
}
