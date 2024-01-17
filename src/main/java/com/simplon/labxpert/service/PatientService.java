package com.simplon.labxpert.service;
import com.simplon.labxpert.model.dto.PatientDTO;
import java.util.List;

public interface PatientService {
    PatientDTO getPatientById(long patientId);
    List<PatientDTO> getAllPatients();
    PatientDTO createPatient(PatientDTO patientDTO);
    PatientDTO updatePatient(long patientId, PatientDTO patientDTO);
    void deletePatient(long patientId);
    PatientDTO getPatientByEmail(String email);
}

