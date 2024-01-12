package com.simplon.labxpert.repository;

import com.simplon.labxpert.LabXpertApplication;
import com.simplon.labxpert.model.entity.Result;
import com.simplon.labxpert.model.enums.ResultStatus;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@SpringJUnitConfig(LabXpertApplication.class)
class ResultRepositoryTest {
    private ResultRepository resultRepository;

    private Result result1;
    private Result result2;
    @Autowired
    public ResultRepositoryTest(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @BeforeEach
    void setUp() {
        // Create some results
        result1 = Result.builder()
                .resultValues(1.5)
                .measurementUnits("mg/L")
                .resultStatus(ResultStatus.ANORMAL)
                .analysis(null)
                .build();
        result2 = Result.builder()
                .resultValues(1.5)
                .measurementUnits("mg/L")
                .resultStatus(ResultStatus.ANORMAL)
                .analysis(null)
                .build();
        result1 = resultRepository.save(result1);
        result2 = resultRepository.save(result2);
    }

    @Test
    @Order(1)
    @DisplayName("Test Result Repository findById Method")
    public void ResultRepository_findById_ReturnResult() {
        // Act
        Optional<Result> result = resultRepository.findById(result1.getResultID());
        // Assert
        assertTrue(result.isPresent());
        assertEquals(result1.getResultID(), result.get().getResultID());
    }

    @Test
    @Order(2)
    @DisplayName("Test Result Repository findAll Method")
    public void ResultRepository_findAll_ReturnResults() {
        // Act
        List<Result> results = resultRepository.findAll();
        // Assert
        assertEquals(2, results.size());
        assertNotNull(results);
        assertTrue(results.iterator().hasNext());
    }

    @Test
    @Order(3)
    @DisplayName("Test Result Repository save Method")
    public void ResultRepository_save_ReturnResult() {
        // Arrange
        Result result3 = Result.builder()
                .resultValues(3.5)
                .measurementUnits("mg/L")
                .resultStatus(ResultStatus.NORMAL)
                .analysis(null)
                .build();
        // Act
        result3 = resultRepository.save(result3);
        // Assert
        assertNotNull(result3);
        assertEquals(result3.getResultID(), resultRepository.findById(result3.getResultID()).get().getResultID());
    }

    @Test
    @Order(4)
    @DisplayName("Test Result Repository deleteById Method")
    public void ResultRepository_deleteById_ReturnVoid() {
        // Act
        resultRepository.deleteById(result1.getResultID());
        Optional<Result> result = resultRepository.findById(result1.getResultID());
        // Assert
        assertFalse(resultRepository.findById(result1.getResultID()).isPresent());
        assertFalse(result.isPresent());
    }

    @Test
    @Order(5)
    @DisplayName("Test Result Repository update Method")
    public void ResultRepository_update_ReturnResult() {
        // Arrange
        Result result3 = Result.builder()
                .resultValues(3.5)
                .measurementUnits("mg/L")
                .resultStatus(ResultStatus.NORMAL)
                .analysis(null)
                .build();
        // Act
        result3 = resultRepository.save(result3);
        result3.setResultValues(4.5);
        result3 = resultRepository.save(result3);
        // Assert
        assertNotNull(result3);
        assertEquals(4.5, resultRepository.findById(result3.getResultID()).get().getResultValues());
    }

    @AfterEach
    void tearDown() {
        resultRepository.deleteAll();
    }
}