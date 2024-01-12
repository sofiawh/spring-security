package com.simplon.labxpert.repository;

import com.simplon.labxpert.LabXpertApplication;
import com.simplon.labxpert.model.entity.*;
import com.simplon.labxpert.model.enums.AnalysisStatus;
import com.simplon.labxpert.model.enums.Gender;
import com.simplon.labxpert.model.enums.SampleStatus;
import com.simplon.labxpert.model.enums.UserRole;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SpringJUnitConfig(LabXpertApplication.class)
class AnalysisRepositoryTest {

    private AnalysisRepository analysisRepository;
    private SampleRepository sampleRepository;
    private ReagentRepository reagentRepository;
    private UserRepository userRepository;
    private PatientRepository patientRepository;
    private AnalysisReagentRepository analysisReagentRepository;
    private Analysis analysis1;
    private Analysis analysis2;
    private Analysis analysis3;
    private Sample sample;
    private Patient patient;
    private Reagent reagent1;
    private Reagent reagent2;
    private User user;

    @Autowired
    public AnalysisRepositoryTest(AnalysisRepository analysisRepository, SampleRepository sampleRepository, ReagentRepository reagentRepository, UserRepository userRepository, PatientRepository patientRepository, AnalysisReagentRepository analysisReagentRepository) {
        this.analysisRepository = analysisRepository;
        this.sampleRepository = sampleRepository;
        this.reagentRepository = reagentRepository;
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.analysisReagentRepository = analysisReagentRepository;
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
        // Create some Reagents
        reagent1 = Reagent.builder()
                .reagentName("reagent1")
                .reagentDescription("reagent1")
                .quantityInStock(100)
                .expirationDate(LocalDate.of(2002, 11, 12))
                .supplier("reagent1")
                .build();
        reagent1 = reagentRepository.save(reagent1);
        reagent2 = Reagent.builder()
                .reagentName("reagent2")
                .reagentDescription("reagent2")
                .quantityInStock(50)
                .expirationDate(LocalDate.of(2002, 11, 12))
                .supplier("reagent2")
                .build();
        reagent2 = reagentRepository.save(reagent2);
        // Create User
        user = User.builder()
                .email("ayoub@gmail.com")
                .emailVerified(true)
                .username("ayoub")
                .password("ayoub")
                .userRole(UserRole.ADMINISTRATOR)
                .personalInfo("ayoub")
                .build();
        user = userRepository.save(user);
        // Create Sample
        sample = Sample.builder()
                .analysisType("test1")
                .sampleDescription("test1")
                .collectionDate(LocalDate.of(2002, 11, 12))
                .sampleStatus(SampleStatus.PENDING)
                .patient(patient)
                .build();
        sample = sampleRepository.save(sample);
        // Create some analyses
        analysis1 = Analysis.builder()
                .startDate(LocalDate.of(2024, 11, 12))
                .endDate(LocalDate.of(2024, 11, 12))
                .analysisStatus(AnalysisStatus.NEED_SCHEDULING)
                .comments("analysis")
                .sample(sample)
                .user(user)
                .build();
        analysis1 = analysisRepository.save(analysis1);
        // Create some analysisReagents
        AnalysisReagent analysisReagent1 = AnalysisReagent.builder()
                .analysis(analysis1)
                .reagent(reagent1)
                .quantity(10)
                .build();
        analysisReagent1 = analysisReagentRepository.save(analysisReagent1);
        AnalysisReagent analysisReagent2 = AnalysisReagent.builder()
                .analysis(analysis1)
                .reagent(reagent2)
                .quantity(5)
                .build();
        analysisReagent2 = analysisReagentRepository.save(analysisReagent2);
    }

    @Test
    @Order(1)
    @DisplayName("Test Analysis Repository findById method")
    public void AnalysisRepository_FindById_ReturnAnalysis() {
        // Act
        Optional<Analysis> optionalAnalysis = analysisRepository.findById(analysis1.getAnalysisID());
        // Assert
        assertTrue(optionalAnalysis.isPresent());
        Analysis analysis = optionalAnalysis.get();
        assertEquals(analysis1.getAnalysisID(), analysis.getAnalysisID());
        assertEquals(analysis1.getStartDate(), analysis.getStartDate());
        assertEquals(analysis1.getEndDate(), analysis.getEndDate());
        assertEquals(analysis1.getAnalysisStatus(), analysis.getAnalysisStatus());
        assertEquals(analysis1.getComments(), analysis.getComments());
        assertEquals(analysis1.getSample().getSampleID(), analysis.getSample().getSampleID());
        assertEquals(analysis1.getUser().getUserID(), analysis.getUser().getUserID());
    }

    @Test
    @Order(2)
    @DisplayName("Test Analysis Repository save method")
    public void AnalysisRepository_Save_ReturnAnalysis() {
        // Arrange
        analysis2 = Analysis.builder()
                .startDate(LocalDate.of(2024, 11, 12))
                .endDate(LocalDate.of(2024, 11, 12))
                .analysisStatus(AnalysisStatus.NEED_SCHEDULING)
                .comments("analysis")
                .sample(sample)
                .user(user)
                .build();
        // Act
        analysis2 = analysisRepository.save(analysis2);
        AnalysisReagent analysisReagent1 = AnalysisReagent.builder()
                .analysis(analysis2)
                .reagent(reagent1)
                .quantity(2)
                .build();
        analysisReagent1 = analysisReagentRepository.save(analysisReagent1);
        // Assert
        assertNotNull(analysis2);
        Analysis savedAnalysis = analysisRepository.findById(analysis2.getAnalysisID()).get();
        assertEquals(analysis2.getAnalysisID(), savedAnalysis.getAnalysisID());
        assertEquals(analysis2.getStartDate(), savedAnalysis.getStartDate());
        assertEquals(analysis2.getEndDate(), savedAnalysis.getEndDate());
        assertEquals(analysis2.getAnalysisStatus(), savedAnalysis.getAnalysisStatus());
        assertEquals(analysis2.getComments(), savedAnalysis.getComments());
        assertEquals(analysis2.getSample().getSampleID(), savedAnalysis.getSample().getSampleID());
        assertEquals(analysis2.getUser().getUserID(), savedAnalysis.getUser().getUserID());
    }

    @Test
    @Order(3)
    @DisplayName("Test Analysis Repository update method")
    public void AnalysisRepository_Update_ReturnUpdatedAnalysis() {
        // Arrange
        analysis1.setComments("Updated comments");
        analysis1.setAnalysisStatus(AnalysisStatus.SCHEDULED);
        // Act
        analysis1 = analysisRepository.save(analysis1);
        // Assert
        Analysis updatedAnalysis = analysisRepository.findById(analysis1.getAnalysisID()).get();
        assertEquals("Updated comments", updatedAnalysis.getComments());
        assertEquals(AnalysisStatus.SCHEDULED, updatedAnalysis.getAnalysisStatus());
    }

    @Test
    @Order(4)
    @DisplayName("Test Analysis Repository delete method")
    public void AnalysisRepository_Delete_ReturnVoid() {
        // Arrange
        analysisReagentRepository.deleteAll();
        analysisRepository.delete(analysis1);
        // Assert
        assertFalse(analysisRepository.findById(analysis1.getAnalysisID()).isPresent());
    }

    @AfterEach
    void tearDown() {
        analysisReagentRepository.deleteAll();
        analysisRepository.deleteAll();
        sampleRepository.deleteAll();
        patientRepository.deleteAll();
        userRepository.deleteAll();
        reagentRepository.deleteAll();
    }
}