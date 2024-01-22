package com.simplon.labxpert.mapper;

import com.simplon.labxpert.model.dto.TestResultDTO;
import com.simplon.labxpert.model.entity.TestResult;
import org.mapstruct.Mapper;

/**
 * Mapper for the TestResult entity.
 * It contains all the methods that we need to map a TestResultDTO to a TestResult and vice versa.
 */
@Mapper(componentModel = "spring")
public interface TestResultMapper extends GlobalMapper<TestResultDTO, TestResult> {
}
