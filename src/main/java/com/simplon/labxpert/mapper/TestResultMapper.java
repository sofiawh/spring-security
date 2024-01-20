package com.simplon.labxpert.mapper;

import com.simplon.labxpert.model.dto.TestResultDTO;
import com.simplon.labxpert.model.entity.TestResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestResultMapper extends GlobalMapper<TestResultDTO, TestResult> {
}
