package com.simplon.labxpert.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplon.labxpert.model.dto.PatientDTO;
import com.simplon.labxpert.model.enums.Gender;
import com.simplon.labxpert.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for the PatientController class.
 * These tests use MockMvc to simulate HTTP requests and verify the behavior of the PatientController.
 */
@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Autowired
    private ObjectMapper objectMapper;

    private PatientDTO newPatient;
    /**
     * Setup method to initialize common objects before each test.
     */
    @BeforeEach
    void setUp() {
        newPatient = new PatientDTO();
        newPatient.setPatientID(1L);
        newPatient.setFirstName("patientFirstName");
        newPatient.setLastName("patientLastName");
        newPatient.setPatientEmail("test@example.com");
        newPatient.setDateOfBirth(LocalDate.of(1990, 1, 1));
        newPatient.setGender(Gender.MALE);
        newPatient.setAddress("123 Street, City");
        newPatient.setPhoneNumber("1234567890");
    }

    /**
     * Test case for retrieving all patients.
     * Verifies that the endpoint returns a list of patients and interacts with the PatientService appropriately.
     */
    @Test
    void getAllPatients() throws Exception {
        List<PatientDTO> fakePatients = Arrays.asList(newPatient);
        when(patientService.getAllPatients()).thenReturn(fakePatients);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/patients"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(fakePatients)));
        verify(patientService, times(1)).getAllPatients();
    }

    /**
     * Test case for creating a new patient.
     * Verifies that the endpoint creates a patient and interacts with the PatientService appropriately.
     */
    @Test
    public void createPatient() throws Exception  {
        when(patientService.createPatient(any())).thenReturn(newPatient);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPatient)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(newPatient)));
        verify(patientService, times(1)).createPatient(any());
    }
    /**
     * Test case for retrieving a patient by ID.
     * Verifies that the endpoint returns a patient based on the provided ID and interacts with the PatientService appropriately.
     */
    @Test
    void getPatientByID() throws Exception {
        when(patientService.getPatientById(anyLong())).thenReturn(newPatient);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/patients/{patientId}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(newPatient)));
        verify(patientService, times(1)).getPatientById(1L);
    }

    /**
     * Test case for updating a patient.
     * Verifies that the endpoint updates a patient and interacts with the PatientService appropriately.
     */
    @Test
    void updatePatient() throws Exception {
        newPatient.setLastName("UpdatedLastName");
        newPatient.setAddress("Updated Address");
        when(patientService.updatePatient(anyLong(), any())).thenReturn(newPatient);
        mockMvc.perform(put("/api/v1/patients/{patientId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPatient)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(newPatient)));
        verify(patientService, times(1)).updatePatient(anyLong(), any());
    }
    /**
     * Test case for deleting a patient.
     * Verifies that the endpoint deletes a patient and interacts with the PatientService appropriately.
     */
    @Test
    void deletePatient() throws Exception {
        mockMvc.perform(delete("/api/v1/patients/{patientId}", 1L))
                .andExpect(status().isNoContent());
        verify(patientService, times(1)).deletePatient(1L);
    }
    /**
     * Test case for retrieving a patient by email.
     * Verifies that the endpoint returns a patient based on the provided email and interacts with the PatientService appropriately.
     */
    @Test
    void getPatientByEmail() throws Exception {
        when(patientService.getPatientByEmail(anyString())).thenReturn(newPatient);
        mockMvc.perform(get("/api/v1/patients/byEmail").param("email", "test@example.com"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(newPatient)));
        verify(patientService, times(1)).getPatientByEmail("test@example.com");
    }
}