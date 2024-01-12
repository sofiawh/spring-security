package com.simplon.labxpert.repository;

import com.simplon.labxpert.LabXpertApplication;
import com.simplon.labxpert.model.entity.Patient;
import com.simplon.labxpert.model.enums.Gender;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SpringJUnitConfig(LabXpertApplication.class)
class PatientRepositoryTest {

    private PatientRepository patientRepository;
    private Patient patient1;
    private Patient patient2;
    private Patient patient3;

    @Autowired
    public PatientRepositoryTest(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @BeforeEach
    void setUp() {
        // Create some patients
        patient1 = Patient.builder()
                .firstName("test3")
                .lastName("test3")
                .patientEmail("test3@gmail.com")
                .address("test3")
                .phoneNumber("test3")
                .dateOfBirth(LocalDate.of(2002, 11, 12))
                .gender(Gender.MALE)
                .build();
        patient2 = Patient.builder()
                .firstName("test1")
                .lastName("test1")
                .patientEmail("test1@gmail.comé")
                .address("test1")
                .phoneNumber("test1")
                .dateOfBirth(LocalDate.of(2002, 11, 12))
                .gender(Gender.FEMALE)
                .build();
        patient3 = Patient.builder()
                .firstName("test2")
                .lastName("test2")
                .patientEmail("")
                .address("test2")
                .phoneNumber("test2")
                .dateOfBirth(LocalDate.of(2002, 11, 12))
                .gender(Gender.FEMALE)
                .build();
        patient1 = patientRepository.save(patient1);
        patient2 = patientRepository.save(patient2);
        patient3 = patientRepository.save(patient3);
    }

    @Test
    @Order(1)
    @DisplayName("Test Patient Repository findAll method")
    public void PatientRepository_FindAll_ReturnAllPatients() {
        // Act
        List<Patient> patients = patientRepository.findAll();
        // Assert
        assertEquals(3, patients.size());
        assertThat(patients).isNotEmpty();
        assertThat(patients.stream().map(Patient::getPatientID))
                .contains(patient1.getPatientID(), patient2.getPatientID(), patient3.getPatientID());
    }

    @Test
    @Order(2)
    @DisplayName("Test Patient Repository findById method")
    public void PatientRepository_FindById_ReturnPatient() {
        // Act
        Patient patient = patientRepository.findById(patient1.getPatientID()).orElse(null);
        // Assert
        assertNotNull(patient);
        assertEquals(patient1.getPatientID(), patient.getPatientID());
    }

    @Test
    @Order(3)
    @DisplayName("Test Patient Repository save method")
    public void PatientRepository_Save_ReturnPatient() {
        // Arrange
        Patient patient = Patient.builder()
                .firstName("test4")
                .lastName("test4")
                .patientEmail("test4@gmail.com")
                .address("test4")
                .phoneNumber("test4")
                .dateOfBirth(LocalDate.of(2002, 11, 12))
                .gender(Gender.FEMALE)
                .build();
        // Act
        Patient savedPatient = patientRepository.save(patient);
        // Assert
        assertNotNull(savedPatient.getPatientID());
        assertEquals(savedPatient, patient);
    }

    @Test
    @Order(4)
    @DisplayName("Test Patient Repository delete method")
    public void PatientRepository_Delete_ReturnVoid() {
        // Act
        patientRepository.delete(patient1);
        // Assert
        List<Patient> patients = patientRepository.findAll();
        assertEquals(2, patients.size());
        assertThat(patients).doesNotContain(patient1);
    }

    @Test
    @Order(5)
    @DisplayName("Test Patient Repository UpdatePatient method")
    public void PatientRepository_UpdatePatient_ReturnVoid() {
        // Arrange
        Patient patientToUpdate = patientRepository.findById(patient1.getPatientID()).orElse(null);
        assertNotNull(patientToUpdate);
        String newEmail = "email@gmail.com";
        patientToUpdate.setPatientEmail(newEmail);
        // Act
        Patient updatedPatient = patientRepository.save(patientToUpdate);
        // Assert
        assertEquals(newEmail, updatedPatient.getPatientEmail());
    }



    @AfterEach
    void tearDown() {
        // delete all users
        patientRepository.deleteAll();
    }
}