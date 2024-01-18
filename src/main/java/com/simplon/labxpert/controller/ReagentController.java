package com.simplon.labxpert.controller;

import com.simplon.labxpert.exception.handler.CustomNotFoundException;
import com.simplon.labxpert.model.dto.ReagentDTO;
import com.simplon.labxpert.model.enums.ReagentStatus;
import com.simplon.labxpert.service.ReagentService;
import com.simplon.labxpert.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reagents")
public class ReagentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final ReagentService reagentService;

    @Autowired
    public ReagentController(ReagentService reagentService) {
        this.reagentService = reagentService;
    }

    @GetMapping
    public ResponseEntity<List<ReagentDTO>> getAllReagents() {
        LOGGER.info("Try To Fetching All Reagents Controller layer");
        List<ReagentDTO> reagentDTOList = reagentService.getAllReagents();
        return new ResponseEntity<>(reagentDTOList, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<List<ReagentDTO>> getAllReagentsByStatus(@RequestParam(required = true) ReagentStatus reagentStatus) {
        LOGGER.info("Try To Fetching All Reagents By Status Controller layer");
        List<ReagentDTO> reagentDTOList = reagentService.getAllReagentsByStatus(reagentStatus);
        return new ResponseEntity<>(reagentDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReagentDTO> getReagentById(@PathVariable long id) {
        LOGGER.info("Try To Fetching Reagent By Id Controller layer");
        ReagentDTO reagentDTO = reagentService.getReagentById(id);
        return new ResponseEntity<>(reagentDTO, HttpStatus.OK);
    }

    // TODO : FIX THE PROBLEM HERE
    @GetMapping("/expired")
    public ResponseEntity<List<ReagentDTO>> getAllExpiredReagents() {
        LOGGER.info("Try To Fetching All Expired Reagents Controller layer");
        List<ReagentDTO> reagentDTOList = reagentService.getAllReagentsThatExpired();
        return new ResponseEntity<>(reagentDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReagentDTO> createReagent(@RequestBody @Valid ReagentDTO reagentDTO) {
        LOGGER.info("Try to create new Reagent Controller layer");
        ReagentDTO reagentCreated = reagentService.addReagent(reagentDTO);
        return new ResponseEntity<>(reagentCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReagent(@PathVariable long id, @RequestBody @Valid ReagentDTO reagentDTO) {
        LOGGER.info("Try to update a specific Reagent Controller layer");
        ReagentDTO reagentUpdated = reagentService.updateReagent(reagentDTO, id);
        return new ResponseEntity<>(reagentUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReagent(@PathVariable long id) {
        LOGGER.info("Try to delete Reagent Controller layer");
        String reagentDeleted = reagentService.deleteReagent(id);
        return new ResponseEntity<>(reagentDeleted, HttpStatus.OK);
    }
}
