package com.simplon.labxpert.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplon.labxpert.model.dto.PatientDTO;
import com.simplon.labxpert.model.dto.SampleDTO;
import com.simplon.labxpert.model.enums.AnalysisType;
import com.simplon.labxpert.model.enums.Gender;
import com.simplon.labxpert.model.enums.SampleStatus;
import com.simplon.labxpert.service.SampleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SampleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SampleService sampleService;
    @Autowired
    private ObjectMapper objectMapper;
    private SampleDTO newSample;

    @BeforeEach
    void setUp() {
        newSample = new SampleDTO();
        newSample.setSampleID(1L);
        newSample.setAnalysisType(AnalysisType.BLOOD_CHEMISTRY);
        newSample.setSampleDescription("TestSample");
        newSample.setCollectionDate(LocalDate.now());
        newSample.setSampleStatus(SampleStatus.PENDING);

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPatientID(1L);
        patientDTO.setFirstName("patientFirstName");
        patientDTO.setLastName("patientLastName");
        patientDTO.setPatientEmail("test@example.com");
        patientDTO.setDateOfBirth(LocalDate.of(1990, 1, 1));
        patientDTO.setGender(Gender.MALE);
        patientDTO.setAddress("123 Street, City");
        patientDTO.setPhoneNumber("1234567890");
        newSample.setPatientDTO(patientDTO);


    }
    @Test
    void getAllSamples() throws Exception {
        List<SampleDTO> fakeSamples = Collections.singletonList(newSample);
        when(sampleService.getAllSimple()).thenReturn(fakeSamples);
        mockMvc.perform(get("/api/v1/samples"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(fakeSamples)));
        verify(sampleService, times(1)).getAllSimple();
    }
    @Test
    void createSample() throws Exception {
        when(sampleService.createSample(any())).thenReturn(newSample);
        mockMvc.perform(post("/api/v1/samples")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSample)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(newSample)));
        verify(sampleService, times(1)).createSample(any());
    }
    @Test
    void getSampleByID() throws Exception {
        when(sampleService.getSampleById(anyLong())).thenReturn(newSample);
        mockMvc.perform(get("/api/v1/samples/{sampleId}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(newSample)));
        verify(sampleService, times(1)).getSampleById(1L);
    }
    @Test
    void deleteSample() throws Exception {
        mockMvc.perform(delete("/api/v1/samples/{sampleId}", 1L))
                .andExpect(status().isNoContent());
        verify(sampleService, times(1)).deleteSample(1L);
    }
}
