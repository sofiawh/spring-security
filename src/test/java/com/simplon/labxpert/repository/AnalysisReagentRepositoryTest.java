package com.simplon.labxpert.repository;

import com.simplon.labxpert.LabXpertApplication;
import com.simplon.labxpert.model.entity.Analysis;
import com.simplon.labxpert.model.entity.AnalysisReagent;
import com.simplon.labxpert.model.entity.Reagent;
import com.simplon.labxpert.model.enums.AnalysisStatus;
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
class AnalysisReagentRepositoryTest {
    private AnalysisReagentRepository analysisReagentRepository;
    private AnalysisRepository analysisRepository;
    private ReagentRepository reagentRepository;
    private Analysis analysis;
    private Reagent reagent;
    private AnalysisReagent analysisReagent1;
    private AnalysisReagent analysisReagent2;

    @Autowired
    public AnalysisReagentRepositoryTest(AnalysisReagentRepository analysisReagentRepository, AnalysisRepository analysisRepository, ReagentRepository reagentRepository) {
        this.analysisReagentRepository = analysisReagentRepository;
        this.analysisRepository = analysisRepository;
        this.reagentRepository = reagentRepository;
    }

    @BeforeEach
    void setUp() {
        // Create an analysis
        analysis = Analysis.builder()
                .startDate(LocalDate.of(2021, 1, 1))
                .endDate(LocalDate.of(2021, 1, 2))
                .comments("comments")
                .analysisStatus(AnalysisStatus.NEED_SCHEDULING)
                .build();
        analysisRepository.save(analysis);
        // Create a reagent
        reagent = Reagent.builder()
                .reagentSerialNumber("JKLLKJ")
                .reagentName("reagentName")
                .reagentDescription("reagentDescription")
                .quantityInStock(10)
                .expirationDate(LocalDate.of(2021, 1, 1))
                .supplier("supplier")
                .build();
        reagentRepository.save(reagent);
        // Create an analysisReagent
        analysisReagent1 = AnalysisReagent.builder()
                .analysis(analysis)
                .reagent(reagent)
                .quantity(1)
                .build();
        analysisReagentRepository.save(analysisReagent1);
        // Create an analysisReagent
        analysisReagent2 = AnalysisReagent.builder()
                .analysis(analysis)
                .reagent(reagent)
                .quantity(2)
                .build();
        analysisReagentRepository.save(analysisReagent2);
    }

    @Test
    @Order(1)
    @DisplayName("Test AnalysisReagentsRepository findById Method")
    public void AnalysisReagentsRepository_findById_ReturnAnalysisReagent() {
        // Act
        Optional<AnalysisReagent> analysisReagentOptional = analysisReagentRepository.findById(analysisReagent1.getAnalysisReagentID());
        // Assert
        assertNotNull(analysisReagentOptional);
        assertTrue(analysisReagentOptional.isPresent());
        assertEquals(analysisReagent1.getAnalysisReagentID(), analysisReagentOptional.get().getAnalysisReagentID());
    }

    @Test
    @Order(2)
    @DisplayName("Test AnalysisReagentsRepository findAll Method")
    public void AnalysisReagentsRepository_findAll_ReturnAnalysisReagentList() {
        // Act
        List<AnalysisReagent> analysisReagentIterable = analysisReagentRepository.findAll();
        // Assert
        assertNotNull(analysisReagentIterable);
        assertTrue(analysisReagentIterable.iterator().hasNext());
        assertEquals(2, analysisReagentIterable.spliterator().getExactSizeIfKnown());
    }

    @Test
    @Order(3)
    @DisplayName("Test AnalysisReagentRepository save and find method")
    public void AnalysisReagentRepository_SaveAndFind_ReturnAnalysisReagent() {
        // Arrange
        AnalysisReagent analysisReagent = AnalysisReagent.builder()
                .analysis(analysis)
                .reagent(reagent)
                .quantity(1)
                .build();

        // Act
        AnalysisReagent savedAnalysisReagent = analysisReagentRepository.save(analysisReagent);
        Optional<AnalysisReagent> retrievedAnalysisReagent = analysisReagentRepository.findById(savedAnalysisReagent.getAnalysisReagentID());

        // Assert
        assertTrue(retrievedAnalysisReagent.isPresent());
        assertEquals(savedAnalysisReagent.getAnalysisReagentID(), retrievedAnalysisReagent.get().getAnalysisReagentID());
    }

    @Test
    @Order(4)
    @DisplayName("Test AnalysisReagentRepository delete method")
    public void AnalysisReagentRepository_Delete_ReturnVoid() {
        // Arrange
        Optional<AnalysisReagent> savedAnalysisReagent = analysisReagentRepository.findById(analysisReagent1.getAnalysisReagentID());
        AnalysisReagent analysisReagent = savedAnalysisReagent.get();
        assertTrue(savedAnalysisReagent.isPresent());
        // Act
        analysisReagentRepository.delete(analysisReagent);
        Optional<AnalysisReagent> deletedAnalysisReagent = analysisReagentRepository.findById(savedAnalysisReagent.get().getAnalysisReagentID());

        // Assert
        assertFalse(deletedAnalysisReagent.isPresent());
    }

    @Test
    @Order(5)
    @DisplayName("Test AnalysisReagentRepository update method")
    public void AnalysisReagentRepository_Update_ReturnUpdatedAnalysisReagent() {
        // Arrange
        Optional<AnalysisReagent> savedAnalysisReagent = analysisReagentRepository.findById(analysisReagent1.getAnalysisReagentID());
        AnalysisReagent analysisReagent = savedAnalysisReagent.get();
        assertTrue(savedAnalysisReagent.isPresent());
        // Act
        analysisReagent.setQuantity(10);
        analysisReagentRepository.save(analysisReagent);
        Optional<AnalysisReagent> updatedAnalysisReagent = analysisReagentRepository.findById(savedAnalysisReagent.get().getAnalysisReagentID());

        // Assert
        assertTrue(updatedAnalysisReagent.isPresent());
        assertEquals(10, updatedAnalysisReagent.get().getQuantity());
    }

    @AfterEach
    void tearDown() {
        analysisReagentRepository.deleteAll();
        analysisRepository.deleteAll();
        reagentRepository.deleteAll();
    }
}