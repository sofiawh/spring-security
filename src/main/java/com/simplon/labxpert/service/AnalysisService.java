package com.simplon.labxpert.service;

import com.simplon.labxpert.model.dto.AnalysisDTO;


import java.util.List;

public interface AnalysisService {
    List<AnalysisDTO> findAllAnalysis();
    AnalysisDTO findAnalysisById(Long id);
    AnalysisDTO createAnalysis(AnalysisDTO analysisDTO);
    AnalysisDTO updateAnalysis(AnalysisDTO analysisDTO);
    void deleteAnalysis(Long id);
}
