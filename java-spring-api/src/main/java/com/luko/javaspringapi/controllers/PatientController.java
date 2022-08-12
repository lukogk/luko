package com.luko.javaspringapi.controllers;


import com.luko.javaspringapi.dto.PatientDto;
import com.luko.javaspringapi.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@GetMapping("/{patientId}")
	public ResponseEntity<PatientDto> getPatientById(@PathVariable UUID patientId) {
		PatientDto patientDto = patientService.getPatientById(patientId);
		return ResponseEntity.ok(patientDto);
	}

	@GetMapping
	public ResponseEntity<List<PatientDto>> getAllPatients() {
		List<PatientDto> patients = patientService.getAllPatients();
		return ResponseEntity.ok(patients);
	}

	@PostMapping
	public ResponseEntity<PatientDto> registerPatient(@RequestBody PatientDto patientDto) {
		PatientDto result =  patientService.registerPatient(patientDto);
		if(result.getId() == null)
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		return ResponseEntity.ok(result);
	}

	@PutMapping("/{patientId}")
	public ResponseEntity<PatientDto> updatePatient(@PathVariable UUID patientId, @RequestBody PatientDto patientDto) {
		PatientDto result = patientService.updatePatient(patientId, patientDto);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{patientId}")
	public ResponseEntity<Boolean> deletePatient(@PathVariable UUID patientId) {
		patientService.deletePatient(patientId);
		return ResponseEntity.ok(true);
	}

}
