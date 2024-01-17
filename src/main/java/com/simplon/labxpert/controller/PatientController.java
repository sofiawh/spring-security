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

/**
 * PatientController is a class that manage patient-related related operations
 * <p>This class interacts with the PatientServices to perform patient-related tasks.</p>
 *
 * @author Chaimaa MAHY
 */

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    private PatientService patientService;
    @Autowired
    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    /**
     * Retrieves a list of all patients in the system.
     *
     * <p>This endpoint is mapped to the HTTP GET method using the {@code @GetMapping} annotation.
     * It returns a {@code ResponseEntity<List<PatientDTO>>} containing a list of {@code PatientDTO} objects.</p>
     *
     * <p>If the operation is successful, it responds with an HTTP status OK (200) and the list of patients.
     * If an exception occurs during the operation, it responds with an HTTP status INTERNAL_SERVER_ERROR (500).</p>
     *
     * @return A {@code ResponseEntity<List<PatientDTO>>} containing a list of {@code PatientDTO} objects.
     *         The HTTP status is OK (200) if the operation is successful, INTERNAL_SERVER_ERROR (500) otherwise.
     */
    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        try {
            List<PatientDTO> patientsDTOS = patientService.getAllPatients();
            return new ResponseEntity<>(patientsDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Create a new Patient and add it to the system.
     *
     * <p>This endpoint is mapped to the HTTP POST method using the {@code @PostMapping} annotation.
     * It returns a {@code ResponseEntity<PatientDTO>} containing a {@code PatientDTO} object.</p>
     *
     * <p>If the operation is successful, it responds with an HTTP status OK (200) and the created patient.
     * If there is a conflict (e.g., duplicate patient information) it responds with an HTTP status CONFLICT(409).
     * If an exception occurs during the operation, it responds with an HTTP status INTERNAL_SERVER_ERROR (500).</p>
     * @param patientDTO A {@code PatientDTO} object representing the patient's details. It is expected in the request body.
     * @return A {@code ResponseEntity<PatientDTO>} containing a {@code PatientDTO} object.
     *         The HTTP status is OK (200) if the operation is successful. if there is a conflict  (e.g., duplicate patient information) it responds with an HTTP status CONFLICT(409).
     *         otherwise it responds with an HTTP status INTERNAL_SERVER_ERROR (500) .
     */

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

    /**
     * Retrieves a Patient by his id  from the system.
     *
     * <p>This endpoint is mapped to the HTTP GET method using the {@code @GetMapping} annotation.
     * It returns a {@code ResponseEntity<PatientDTO>} containing a {@code PatientDTO} object.</p>
     *
     * @param patientId A {@code patientId} object representing the patient's id. It is expected in the path variable.
     * <p>If the operation is successful, it responds with an HTTP status OK (200) and the patient retrieved.
     * If there it responds with an HTTP status CONFLICT(409).
     * If an exception occurs during the operation, it responds with an HTTP status INTERNAL_SERVER_ERROR (500).</p>
     *
     * @return A {@code ResponseEntity<PatientDTO>} containing a {@code PatientDTO} object.
     *         The HTTP status is OK (200) if the operation is successful. if there is a conflict it responds with an HTTP status CONFLICT(409) otherwise if there is an exception it responds with an HTTP status INTERNAL_SERVER_ERROR (500) .
     */

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

    // TODO : check if the email passed exists
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





