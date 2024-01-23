package com.simplon.labxpert.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplon.labxpert.model.dto.ReagentDTO;
import com.simplon.labxpert.model.enums.ReagentStatus;
import com.simplon.labxpert.service.ReagentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * This class contains test cases for the ReagentController class.
 * It uses MockMvc to simulate HTTP requests and verifies the behavior of ReagentController.
 */
@SpringBootTest
@AutoConfigureMockMvc
class ReagentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReagentService reagentService;
    @Autowired
    private ObjectMapper objectMapper;
    private ReagentDTO newReagent;
    /**
     * Setup method to initialize common objects before each test.
     */
    @BeforeEach
    void setUp() {
        newReagent = new ReagentDTO();
        newReagent.setReagentID(1L);
        newReagent.setReagentSerialNumber("ABC123");
        newReagent.setReagentName("TestReagent");
        newReagent.setReagentDescription("Test description");
        newReagent.setQuantityInStock(50);
        newReagent.setExpirationDate(LocalDateTime.now().plusMonths(6));
        newReagent.setSupplier("TestSupplier");
        newReagent.setReagentStatus(ReagentStatus.IN_STOCK_VALID);
    }
    /**
     * Test case for retrieving all reagents.
     * Verifies that the endpoint returns a list of reagents and interacts with ReagentService appropriately.
     */
    @Test
    void getAllReagents() throws Exception {
        List<ReagentDTO> fakeReagents = Collections.singletonList(newReagent);
        when(reagentService.getAllReagents()).thenReturn(fakeReagents);
        mockMvc.perform(get("/api/v1/reagents"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(fakeReagents)));
        verify(reagentService, times(1)).getAllReagents();
    }
    /**
     * Test case for retrieving all reagents by status.
     * Verifies that the endpoint returns a list of reagents filtered by status and interacts with ReagentService appropriately.
     */
    @Test
    void getAllReagentsByStatus() throws Exception {
        List<ReagentDTO> fakeReagents = Collections.singletonList(newReagent);
        when(reagentService.getAllReagentsByStatus(any())).thenReturn(fakeReagents);
        mockMvc.perform(get("/api/v1/reagents/status")
                        .param("reagentStatus", "IN_STOCK_VALID"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(fakeReagents)));
        verify(reagentService, times(1)).getAllReagentsByStatus(ReagentStatus.IN_STOCK_VALID);
    }
    /**
     * Test case for retrieving a reagent by ID.
     * Verifies that the endpoint returns a reagent with the given ID and interacts with ReagentService appropriately.
     */
    @Test
    void getReagentById() throws Exception {
        when(reagentService.getReagentById(anyLong())).thenReturn(newReagent);
        mockMvc.perform(get("/api/v1/reagents/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(newReagent)));
        verify(reagentService, times(1)).getReagentById(1L);
    }
    /**
     * Test case for retrieving all expired reagents.
     * Verifies that the endpoint returns a list of expired reagents and interacts with ReagentService appropriately.
     */
    @Test
    void getAllExpiredReagents() throws Exception {
        List<ReagentDTO> fakeExpiredReagents = Collections.singletonList(newReagent);
        when(reagentService.getAllReagentsThatExpired()).thenReturn(fakeExpiredReagents);
        mockMvc.perform(get("/api/v1/reagents/expired"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(fakeExpiredReagents)));
        verify(reagentService, times(1)).getAllReagentsThatExpired();
    }
    /**
     * Test case for retrieving all reagents that will expire soon.
     * Verifies that the endpoint returns a list of reagents that will expire soon and interacts with ReagentService appropriately.
     */
    @Test
    void getAllReagentsThatWillExpireSoon() throws Exception {
        List<ReagentDTO> fakeReagentsSoonToExpire = Collections.singletonList(newReagent);
        when(reagentService.getAllReagentsThatWillExpireSoon(anyInt())).thenReturn(fakeReagentsSoonToExpire);
        mockMvc.perform(get("/api/v1/reagents/expiring-soon")
                        .param("days", "7"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(fakeReagentsSoonToExpire)));
        verify(reagentService, times(1)).getAllReagentsThatWillExpireSoon(7);
    }
    /**
     * Test case for creating a new reagent.
     * Verifies that the endpoint creates a reagent and interacts with ReagentService appropriately.
     */
    @Test
    void createReagent() throws Exception {
        when(reagentService.addReagent(any())).thenReturn(newReagent);
        mockMvc.perform(post("/api/v1/reagents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newReagent)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(newReagent)));
        verify(reagentService, times(1)).addReagent(any());
    }
    /**
     * Test case for updating a reagent.
     * Verifies that the endpoint updates a reagent and interacts with ReagentService appropriately.
     */
    @Test
    void updateReagent() throws Exception {
        newReagent.setReagentDescription("Updated description");
        newReagent.setQuantityInStock(60);
        when(reagentService.updateReagent(any(), anyLong())).thenReturn(newReagent);
        mockMvc.perform(put("/api/v1/reagents/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newReagent)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(newReagent)));
        verify(reagentService, times(1)).updateReagent(any(), anyLong());
    }
    /**
     * Test case for deleting a reagent.
     * Verifies that the endpoint deletes a reagent and interacts with ReagentService appropriately.
     */
    @Test
    void deleteReagent() throws Exception {
        mockMvc.perform(delete("/api/v1/reagents/{id}", 1L))
                .andExpect(status().isOk());
        verify(reagentService, times(1)).deleteReagent(1L);
    }


}
