package com.simplon.labxpert.repository;

import com.simplon.labxpert.LabXpertApplication;
import com.simplon.labxpert.model.entity.Patient;
import com.simplon.labxpert.model.entity.Sample;
import com.simplon.labxpert.model.enums.Gender;
import com.simplon.labxpert.model.enums.SampleStatus;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.junit.jupiter.api.Assertions.*;


@SpringJUnitConfig(LabXpertApplication.class)
@SpringBootTest
class SampleRepositoryTest {

    private SampleRepository sampleRepository;
    private PatientRepository patientRepository;
    private Sample sample1;
    private Sample sample2;
    private Sample sample3;
    private Patient patient;

    @Autowired
    public SampleRepositoryTest(SampleRepository sampleRepository, PatientRepository patientRepository) {
        this.sampleRepository = sampleRepository;
        this.patientRepository = patientRepository;
    }

    @BeforeEach
    void setUp() {
        // Create Patient
        patient = Patient.builder()
                .firstName("test3")
                .lastName("test3")
                .patientEmail("test3@gmail.com")
                .address("test3")
                .phoneNumber("test3")
                .dateOfBirth(LocalDate.of(2002, 11, 12))
                .gender(Gender.MALE)
                .build();
        patient = patientRepository.save(patient);
        // Create some samples
        sample1 = Sample.builder()
                .analysisType("test1")
                .sampleDescription("test1")
                .collectionDate(LocalDate.of(2002, 11, 12))
                .sampleStatus(SampleStatus.PENDING)
                .patient(patient)
                .build();
        sample2 = Sample.builder()
                .analysisType("test2")
                .sampleDescription("test2")
                .collectionDate(LocalDate.of(2002, 11, 12))
                .sampleStatus(SampleStatus.PENDING)
                .patient(patient)
                .build();
        sample3 = Sample.builder()
                .analysisType("test3")
                .sampleDescription("test3")
                .collectionDate(LocalDate.of(2002, 11, 12))
                .sampleStatus(SampleStatus.PENDING)
                .patient(patient)
                .build();
        sample1 = sampleRepository.save(sample1);
        sample2 = sampleRepository.save(sample2);
        sample3 = sampleRepository.save(sample3);
    }

    @Test
    @Order(3)
    @DisplayName("Test SampleRepository Save Method")
    public void SampleRepository_Save_ReturnSavedSample() {
        // Arrange
        Sample sampleSaved = Sample.builder()
                .analysisType("test")
                .sampleDescription("test")
                .collectionDate(LocalDate.of(2002, 11, 12))
                .sampleStatus(SampleStatus.PENDING)
                .patient(patient)
                .build();
        // Act
        sampleSaved = sampleRepository.save(sampleSaved);
        // Assert
        assertNotNull(sampleSaved);
        assertEquals(sampleSaved.getAnalysisType(), "test");
        assertEquals(sampleSaved.getSampleDescription(), "test");
        assertEquals(sampleSaved.getCollectionDate(), LocalDate.of(2002, 11, 12));
        assertEquals(sampleSaved.getSampleStatus(), SampleStatus.PENDING);
        assertEquals(sampleSaved.getPatient(), sample1.getPatient());
    }

    @Test
    @Order(2)
    @DisplayName("Test SampleRepository findAll method")
    public void SampleRepository_FindAll_ReturnAllSamples() {
        // Act
        List<Sample> samples = sampleRepository.findAll();
        // Assert
        assertNotNull(samples);
        assertThat(samples).isNotEmpty();
        assertThat(samples).hasSize(3);
    }

    @Test
    @Order(1)
    @DisplayName("Test SampleRepository findById method")
    public void SampleRepository_FindById_ReturnSample() {
        // Act
        Optional<Sample> sampleOptional = sampleRepository.findById(sample1.getSampleID());
        // Assert
        assertTrue(sampleOptional.isPresent());
        Sample sample = sampleOptional.get();
        assertNotNull(sample);
        assertEquals(sample1.getSampleID(), sample.getSampleID());
    }

    @Test
    @Order(4)
    @DisplayName("Test SampleRepository delete method")
    public void SampleRepository_Delete_ReturnVoid() {
        // Act
        sampleRepository.delete(sample1);
        // Assert
        assertFalse(sampleRepository.existsById(sample1.getSampleID()));
    }

    @Test
    @Order(5)
    @DisplayName("Test SampleRepository deleteAll method")
    public void SampleRepository_DeleteAll_ReturnVoid() {
        // Act
        sampleRepository.deleteAll();
        // Assert
        assertFalse(sampleRepository.existsById(sample1.getSampleID()));
        assertFalse(sampleRepository.existsById(sample2.getSampleID()));
        assertFalse(sampleRepository.existsById(sample3.getSampleID()));
    }

    @Test
    @Order(6)
    @DisplayName("Test SampleRepository update method")
    public void SampleRepository_Update_ReturnUpdatedSample() {
        // Arrange
        Optional<Sample> sampleOptional = sampleRepository.findById(sample3.getSampleID());
        assertThat(sampleOptional).isPresent();
        Sample sampleToUpdate = sampleOptional.get();
        sampleToUpdate.setAnalysisType("analysisType");
        sampleToUpdate.setSampleDescription("sampleDescription");
        // Act
        sampleToUpdate = sampleRepository.save(sampleToUpdate);
        // Assert
        assertNotNull(sampleToUpdate);
        assertEquals(sampleToUpdate.getAnalysisType(), "analysisType");
        assertEquals(sampleToUpdate.getSampleDescription(), "sampleDescription");
        assertThat(sampleToUpdate.getPatient().getPatientEmail()).isEqualTo(patient.getPatientEmail());
    }

    @AfterEach
    void tearDown() {
        sampleRepository.deleteAll();
        patientRepository.deleteAll();
    }
}