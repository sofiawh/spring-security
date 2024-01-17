package com.simplon.labxpert.service.impl;

import com.simplon.labxpert.mapper.PatientMapper;
import com.simplon.labxpert.model.dto.PatientDTO;
import com.simplon.labxpert.model.entity.Patient;
import com.simplon.labxpert.repository.PatientRepository;
import com.simplon.labxpert.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public PatientDTO getPatientById(long patientId) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            return patientMapper.toDTO(patient);
        } else {
            throw new NoSuchElementException("Patient not found with ID: " + patientId);
        }
    }

    @Override
    public List<PatientDTO> getAllPatients() {
       List<Patient> patients= patientRepository.findAll();
       return  patients.stream().map(patientMapper ::toDTO).collect(Collectors.toList());
    }

    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = patientMapper.toEntity(patientDTO);
        Optional<Patient> optionalPatient = patientRepository.findByPatientEmail(patient.getPatientEmail());
        if (optionalPatient.isPresent()){
            // TODO: throw exception
        }
        Patient savedPatient = patientRepository.save(patient);
        return patientMapper.toDTO(savedPatient);
    }

    @Override
    public PatientDTO updatePatient(long patientId,PatientDTO patientDTO) {
        Patient existingPatient = patientRepository.findById(patientId).orElseThrow(()-> new NoSuchElementException("Patient not found with ID: " + patientId));
        existingPatient.setFirstName(patientDTO.getFirstName());
        existingPatient.setLastName(patientDTO.getLastName());
        // TODO : validation of email
        existingPatient.setPatientEmail(patientDTO.getPatientEmail());
        existingPatient.setGender(patientDTO.getGender());
        existingPatient.setAddress(patientDTO.getAddress());
        existingPatient.setPhoneNumber(patientDTO.getPhoneNumber());
        Patient savedPatient = patientRepository.save(existingPatient);
        return patientMapper.toDTO(savedPatient);
    }

    @Override
    public void deletePatient(long patientId) {
        Patient existingPatient = patientRepository.findById(patientId).orElseThrow(()-> new NoSuchElementException("Patient not found with ID: " + patientId));
        patientRepository.deleteById(patientId);
    }

    @Override
    public PatientDTO getPatientByEmail(String email) {
        Optional<Patient> patient = patientRepository.findByPatientEmail(email);
        if(patient.isPresent()){
            Patient existingPatient = patient.get();
            return patientMapper.toDTO(existingPatient);
        }else{
            throw new IllegalArgumentException("Patient with that email is not found" + email);
        }
    }
}
