package com.simplon.labxpert.controller;

import com.simplon.labxpert.model.dto.AnalysisDTO;
import com.simplon.labxpert.service.AnalysisService;
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
@RequestMapping("/api/v1/analysis")
public class AnalysisController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisController.class);

    private final AnalysisService analysisService;

    @Autowired
    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @GetMapping
    public ResponseEntity<List<AnalysisDTO>> getAllAnalysis() {
        LOGGER.info("Getting all analysis");
        List<AnalysisDTO> analysisDTOList = analysisService.findAllAnalysis();
        return new ResponseEntity<>(analysisDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalysisDTO> getAnalysisById(@PathVariable Long id) {
        LOGGER.info("Getting analysis with id : {}", id);
        AnalysisDTO analysisDTO = analysisService.findAnalysisById(id);
        return new ResponseEntity<>(analysisDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<AnalysisDTO> createAnalysis(@Valid @RequestBody AnalysisDTO analysisDTO) {
        LOGGER.info("Creating analysis from controller");
        AnalysisDTO analysisDTOCreated = analysisService.createAnalysis(analysisDTO);
        return new ResponseEntity<>(analysisDTOCreated, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnalysis(@PathVariable Long id) {
        LOGGER.info("Deleting analysis with id : {}", id);
        String message = analysisService.deleteAnalysis(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
