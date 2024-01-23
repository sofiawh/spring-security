package com.simplon.labxpert.controller;

import com.simplon.labxpert.model.dto.AnalysisReagentDTO;
import com.simplon.labxpert.service.AnalysisReagentService;
import com.simplon.labxpert.service.impl.AnalysisServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/analysis-reagent")
public class AnalysisReagentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisReagentController.class);
    private final AnalysisReagentService analysisReagentService;

    @Autowired
    public AnalysisReagentController(AnalysisReagentService analysisReagentService) {
        this.analysisReagentService = analysisReagentService;
    }

    @GetMapping
    public ResponseEntity<List<AnalysisReagentDTO>> getAllAnalysisReagents() {
        LOGGER.info("Getting all analysis reagents");
        return new ResponseEntity(analysisReagentService.getAllAnalysisReagents(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AnalysisReagentDTO> createAnalysisReagent(@Valid @RequestBody AnalysisReagentDTO analysisReagentDTO) {
        LOGGER.info("Creating analysis reagent");
        AnalysisReagentDTO analysisReagentDTO1 = analysisReagentService.createAnalysisReagent(analysisReagentDTO);
        return new ResponseEntity<>(analysisReagentDTO1, HttpStatus.CREATED);
    }
}
