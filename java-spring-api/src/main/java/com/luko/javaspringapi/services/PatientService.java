package com.luko.javaspringapi.services;

import com.luko.javaspringapi.dto.PatientDto;
import com.luko.javaspringapi.models.Patient;
import com.luko.javaspringapi.repositories.PatientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public PatientDto registerPatient(PatientDto patientDto) {
        PatientDto patientOptional =  getPatientById(patientDto.getId());
        if(patientOptional.getId() == null){
            Patient patient = new Patient();
            patient.setId(patientDto.getId());
            patient.setFirstName(patientDto.getFirstName());
            patient.setLastName(patientDto.getLastName());
            patient.setDob(patientDto.getDob());
            patient.setSsn(patientDto.getSsn());
            patient.setPhoneNumber(patientDto.getPhoneNumber());
            patientRepository.save(patient);
            patientDto.setId(patient.getId());
            return patientDto;
        }
        return new PatientDto();
    }

    public List<PatientDto> getAllPatients() {
        return patientRepository.getPatients().stream().map( x -> new PatientDto(x.getId(),x.getFirstName(),
                x.getLastName(),x.getDob(),x.getSsn(),x.getPhoneNumber())).collect(Collectors.toList());
    }

    public PatientDto getPatientById(UUID patientId) {
        Optional<Patient> patient = patientRepository.getPatientById(patientId);
        PatientDto patientDto = new PatientDto();
        if (patient.isPresent()){
            BeanUtils.copyProperties(patient.get(), patientDto);
            return patientDto;
        }
        return patientDto;
    }

    public PatientDto updatePatient(UUID patientId, PatientDto patientDto) {
        Optional<Patient> patient = patientRepository.updatePatient(patientId, patientDto);
        PatientDto result = new PatientDto();
        if (patient.isPresent()){
            BeanUtils.copyProperties(patient.get(), result);
        }
        return result;
    }

    public void deletePatient(UUID patientId) {
        patientRepository.deletePatient(patientId);
    }
}
