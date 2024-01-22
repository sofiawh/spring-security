package com.simplon.labxpert.service;

import com.simplon.labxpert.model.dto.TestResultDTO;
import java.util.List;

public interface TestResultService {
   List<TestResultDTO> getAllTestResults();
   TestResultDTO getTestResultById(long  testResultId);
   TestResultDTO updateTestResult(TestResultDTO testResultDTO , long id);
   void deleteTestResult(long testResultId);

}
