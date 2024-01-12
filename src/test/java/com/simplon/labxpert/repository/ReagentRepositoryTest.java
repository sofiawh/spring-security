package com.simplon.labxpert.repository;

import com.simplon.labxpert.LabXpertApplication;
import com.simplon.labxpert.model.entity.Reagent;
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
class ReagentRepositoryTest {
    private ReagentRepository reagentRepository;
    private Reagent reagent1;
    private Reagent reagent2;


    @Autowired
    public ReagentRepositoryTest(ReagentRepository reagentRepository) {
        this.reagentRepository = reagentRepository;
    }

    @BeforeEach
    void setUp() {
        reagent1 = Reagent.builder()
                .reagentSerialNumber("1KDKKLL")
                .reagentName("reagent1")
                .reagentDescription("reagent1")
                .quantityInStock(10)
                .supplier("supplier1")
                .expirationDate(LocalDate.of(2024, 11, 12))
                .build();
        reagent2 = Reagent.builder()
                .reagentSerialNumber("2KDKKLL")
                .reagentName("reagent2")
                .reagentDescription("reagent2")
                .quantityInStock(20)
                .supplier("supplier2")
                .expirationDate(LocalDate.of(2025, 11, 12))
                .build();
        reagentRepository.save(reagent1);
        reagentRepository.save(reagent2);
    }

    @Test
    @Order(1)
    @DisplayName("Test Reagent Repository findById Method")
    public void ReagentRepository_findById_ReturnReagent() {
        // act
        Optional<Reagent> optionalReagent = reagentRepository.findById(reagent1.getReagentID());
        // assert
        assertTrue(optionalReagent.isPresent());
        assertEquals(reagent1.getReagentName(), optionalReagent.get().getReagentName());
        assertEquals(reagent1.getReagentDescription(), optionalReagent.get().getReagentDescription());
        assertEquals(reagent1.getQuantityInStock(), optionalReagent.get().getQuantityInStock());
        assertEquals(reagent1.getExpirationDate(), optionalReagent.get().getExpirationDate());
        assertEquals(reagent1.getSupplier(), optionalReagent.get().getSupplier());
    }

    @Test
    @Order(2)
    @DisplayName("Test Reagent Repository findAll Method")
    public void ReagentRepository_findAll_ReturnReagentList() {
        // act
        List<Reagent> reagentList = reagentRepository.findAll();
        // assert
        assertNotNull(reagentList);
        assertTrue(reagentList.iterator().hasNext());
        assertEquals(reagentList.size(), 2);
        assertEquals(reagent1.getReagentName(), reagentList.get(0).getReagentName());
        assertEquals(reagent1.getReagentDescription(), reagentList.get(0).getReagentDescription());
        assertEquals(reagent1.getQuantityInStock(), reagentList.get(0).getQuantityInStock());
        assertEquals(reagent1.getExpirationDate(), reagentList.get(0).getExpirationDate());
        assertEquals(reagent1.getSupplier(), reagentList.get(0).getSupplier());
    }

    @Test
    @Order(3)
    @DisplayName("Test Reagent Repository save Method")
    public void ReagentRepository_save_ReturnReagent() {
        // arrange
        Reagent reagent3 = Reagent.builder()
                .reagentSerialNumber("3KDKKLL")
                .reagentName("reagent3")
                .reagentDescription("reagent3")
                .quantityInStock(30)
                .supplier("supplier3")
                .expirationDate(LocalDate.of(2026, 11, 12))
                .build();
        // act
        reagent3 = reagentRepository.save(reagent3);
        // assert
        assertNotNull(reagent3);
        assertEquals(reagent3.getReagentID(), reagentRepository.findById(reagent3.getReagentID()).get().getReagentID());
        assertEquals(reagent3.getReagentName(), reagentRepository.findById(reagent3.getReagentID()).get().getReagentName());
        assertEquals(reagent3.getReagentDescription(), reagentRepository.findById(reagent3.getReagentID()).get().getReagentDescription());
        assertEquals(reagent3.getQuantityInStock(), reagentRepository.findById(reagent3.getReagentID()).get().getQuantityInStock());
        assertEquals(reagent3.getExpirationDate(), reagentRepository.findById(reagent3.getReagentID()).get().getExpirationDate());
        assertEquals(reagent3.getSupplier(), reagentRepository.findById(reagent3.getReagentID()).get().getSupplier());
    }

    @Test
    @Order(4)
    @DisplayName("Test Reagent Repository delete Method")
    public void ReagentRepository_delete_ReturnVoid() {
        // act
        reagentRepository.delete(reagent1);
        Optional<Reagent> optionalReagent = reagentRepository.findById(reagent1.getReagentID());
        // assert
        assertFalse(reagentRepository.findById(reagent1.getReagentID()).isPresent());
        assertFalse(optionalReagent.isPresent());
    }

    @Test
    @Order(5)
    @DisplayName("Test Reagent Repository update Method")
    public void ReagentRepository_update_ReturnUpdatedReagent() {
        // arrange
        reagent1.setReagentName("updated reagent1");
        reagent1.setReagentDescription("updated reagent1");
        reagent1.setQuantityInStock(100);
        reagent1.setExpirationDate(LocalDate.of(2027, 11, 12));
        reagent1.setSupplier("updated supplier1");
        // act
        reagent1 = reagentRepository.save(reagent1);
        // assert
        assertEquals(reagent1.getReagentName(), reagentRepository.findById(reagent1.getReagentID()).get().getReagentName());
        assertEquals(reagent1.getReagentDescription(), reagentRepository.findById(reagent1.getReagentID()).get().getReagentDescription());
        assertEquals(reagent1.getQuantityInStock(), reagentRepository.findById(reagent1.getReagentID()).get().getQuantityInStock());
        assertEquals(reagent1.getExpirationDate(), reagentRepository.findById(reagent1.getReagentID()).get().getExpirationDate());
        assertEquals(reagent1.getSupplier(), reagentRepository.findById(reagent1.getReagentID()).get().getSupplier());
    }


    @AfterEach
    void tearDown() {
        reagentRepository.deleteAll();
    }
}