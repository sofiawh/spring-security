package com.simplon.labxpert.controller;

import com.simplon.labxpert.model.dto.AnalysisDTO;
import com.simplon.labxpert.service.AnalysisService;
import com.simplon.labxpert.service.impl.AnalysisServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/analysis")
public class AnalysisController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisController.class);

    private final AnalysisService analysisService;

    @Autowired
    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping
    public ResponseEntity<AnalysisDTO> createAnalysis(@Valid @RequestBody AnalysisDTO analysisDTO) {
        LOGGER.info("Creating analysis from controller");
        AnalysisDTO analysisDTOCreated = analysisService.createAnalysis(analysisDTO);
        return new ResponseEntity<>(analysisDTOCreated, HttpStatus.CREATED);
    }
}
