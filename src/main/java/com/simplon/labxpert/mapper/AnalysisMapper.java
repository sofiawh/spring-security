package com.simplon.labxpert.mapper;

import com.simplon.labxpert.model.dto.AnalysisDTO;
import com.simplon.labxpert.model.entity.Analysis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the Analysis entity.
 * It contains all the methods that we need to map a AnalysisDTO to a Analysis and vice versa.
 */
@Mapper(componentModel = "spring")
public interface AnalysisMapper {


    @Mapping(target = "sampleDTO", source = "sample")
    @Mapping(target = "analysisReagentDTOs", source = "analysisReagents")
    AnalysisDTO toDTO(Analysis analysis);


    @Mapping(target = "sample", source = "sampleDTO")
    @Mapping(target = "analysisReagents", source = "analysisReagentDTOs")
    Analysis toEntity(AnalysisDTO analysisDTO);
}
