package com.simplon.labxpert.controller;

import com.simplon.labxpert.model.dto.PatientDTO;
import com.simplon.labxpert.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    private PatientService patientService;
    @Autowired
    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        try {
            List<PatientDTO> patientsDTOS = patientService.getAllPatients();
            return new ResponseEntity<>(patientsDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@Valid @RequestBody PatientDTO patientDTO) {
        try {
                PatientDTO createdPatient = patientService.createPatient(patientDTO);
                return new ResponseEntity<>(createdPatient, HttpStatus.OK);
        }catch (DataIntegrityViolationException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{patientId}")
    public  ResponseEntity<PatientDTO> getPatientByID( @PathVariable long patientId ){
        try {
            PatientDTO patient = patientService.getPatientById(patientId);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }


    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientDTO> updatePatient( @PathVariable long patientId,@Valid @RequestBody PatientDTO patient){
        try {
            PatientDTO updatedPatient = patientService.updatePatient(patientId,patient);
            return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> deletePatient( @PathVariable long patientId){
        try {
            patientService.deletePatient(patientId);
            return ResponseEntity.noContent().build();
        }catch (NoSuchElementException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
    }

    @GetMapping("/byEmail")
    public ResponseEntity<PatientDTO>  getPatientByEmail(@RequestParam String email){
        try {
            PatientDTO patient = patientService.getPatientByEmail(email);
            return new ResponseEntity<>(patient , HttpStatus.OK);

        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }


    }

}





