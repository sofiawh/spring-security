package com.simplon.labxpert.service.impl;

import com.simplon.labxpert.model.dto.TestResultDTO;
import com.simplon.labxpert.service.TestResultService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestResultServiceImpl implements TestResultService {


    @Override
    public List<TestResultDTO> getAllTestResults() {
        return null;
    }

    @Override
    public TestResultDTO getTestResultById(long testResultId) {
        return null;
    }

    @Override
    public TestResultDTO updateTestResult(TestResultDTO testResultDTO, long id) {
        return null;
    }

    @Override
    public void deleteTestResult(long testResultId) {

    }
}
