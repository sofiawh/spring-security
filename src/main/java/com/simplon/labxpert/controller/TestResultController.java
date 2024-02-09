package com.simplon.labxpert.controller;

import com.simplon.labxpert.model.dto.TestResultDTO;
import com.simplon.labxpert.service.TestResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class for managing test results.
 *
 * @Author Ayoub Ait Si Ahmad
 */
@RestController
@RequestMapping("/api/v1/test-result")
public class TestResultController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestResultController.class);
    private final TestResultService testResultService;

    @Autowired
    public TestResultController(TestResultService testResultService) {
        this.testResultService = testResultService;
    }

    /**
     * This method allows to get all test results.
     *
     * @return a list of all test results.
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_ADMIN', 'SCOPE_ROLE_LABORATORY_MANAGER', 'SCOPE_ROLE_TECHNICIAN')")
    public ResponseEntity<List<TestResultDTO>> getAllTestResults() {
        LOGGER.info("Getting all test results");
        return new ResponseEntity<>(testResultService.getAllTestResults(), HttpStatus.OK);
    }


}
