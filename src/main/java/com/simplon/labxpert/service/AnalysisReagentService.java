package com.simplon.labxpert.service;

import com.simplon.labxpert.model.dto.AnalysisReagentDTO;

import java.util.List;

public interface AnalysisReagentService {
    AnalysisReagentDTO createAnalysisReagent(AnalysisReagentDTO analysisReagentDTO);
    List<AnalysisReagentDTO> getAllAnalysisReagents();
}
