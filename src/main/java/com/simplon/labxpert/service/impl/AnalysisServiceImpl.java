package com.simplon.labxpert.service.impl;

import com.simplon.labxpert.exception.handler.CustomNotFoundException;
import com.simplon.labxpert.mapper.AnalysisMapper;
import com.simplon.labxpert.model.dto.AnalysisDTO;
import com.simplon.labxpert.model.entity.Analysis;
import com.simplon.labxpert.model.entity.Sample;
import com.simplon.labxpert.model.enums.AnalysisStatus;
import com.simplon.labxpert.model.enums.ResultStatus;
import com.simplon.labxpert.repository.AnalysisRepository;
import com.simplon.labxpert.repository.SampleRepository;
import com.simplon.labxpert.service.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisServiceImpl.class);

    private final AnalysisRepository analysisRepository;
    private final SampleRepository sampleRepository;
    private final AnalysisMapper analysisMapper;

    @Autowired
    public AnalysisServiceImpl(AnalysisRepository analysisRepository,
                               AnalysisMapper analysisMapper,
                               SampleRepository sampleRepository) {
        this.analysisRepository = analysisRepository;
        this.analysisMapper = analysisMapper;
        this.sampleRepository = sampleRepository;
    }

    @Override
    public List<AnalysisDTO> findAllAnalysis() {
        LOGGER.info("Getting all analysis");
        List<Analysis> analysisList = analysisRepository.findAll();
        return analysisList.stream().map(analysisMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public AnalysisDTO findAnalysisById(Long id) {
        LOGGER.info("Getting analysis with id : {}", id);
        Optional<Analysis> analysis = analysisRepository.findById(id);
        if (!analysis.isPresent()) {
            LOGGER.error("Analysis not found with id : {}", id);
            throw new CustomNotFoundException("Analysis not found with id : " + id, HttpStatus.NOT_FOUND);
        }
        return analysisMapper.toDTO(analysis.get());
    }

    @Override
    public AnalysisDTO createAnalysis(AnalysisDTO analysisDTO) {
        LOGGER.info("Creating analysis");
        Analysis analysis = analysisMapper.toEntity(analysisDTO);
        Optional<Sample> sample = sampleRepository.findById(analysisDTO.getSampleDTO().getSampleID());
        if (!sample.isPresent()) {
            LOGGER.error("Sample not found with id : {}", analysisDTO.getSampleDTO().getSampleID());
            throw new CustomNotFoundException("Sample not found with id : " + analysisDTO.getSampleDTO().getSampleID(), HttpStatus.NOT_FOUND);
        }
        // check if the sample is already analyzed
        if (sample.get().getAnalysis() != null) {
            LOGGER.error("Sample already analyzed");
            throw new CustomNotFoundException("Sample already analyzed", HttpStatus.BAD_REQUEST);
        }
        // check if the start date is before the end date
        if (analysis.getStartDate().isAfter(analysis.getEndDate())) {
            LOGGER.error("Start date must be before the end date");
            throw new CustomNotFoundException("Start date must be before the end date", HttpStatus.BAD_REQUEST);
        }
        // check if the reagents are available and if the quantity is enough
        analysis.setSample(sample.get());
        analysis.setAnalysisStatus(AnalysisStatus.NEED_SCHEDULING);
        analysis.setResultStatus(ResultStatus.WITHOUT_RESULT_YET);

        analysis = analysisRepository.save(analysis);
        LOGGER.info("Analysis created");
        return analysisMapper.toDTO(analysis);
    }

    @Override
    public AnalysisDTO updateAnalysis(AnalysisDTO analysisDTO) {
        return null;
    }

    @Override
    public String deleteAnalysis(Long id) {
        LOGGER.info("Deleting analysis with id : {}", id);
        Optional<Analysis> analysis = analysisRepository.findById(id);
        if (!analysis.isPresent()) {
            LOGGER.error("Analysis not found with id : {}", id);
            throw new CustomNotFoundException("Analysis not found with id : " + id, HttpStatus.NOT_FOUND);
        }
        analysisRepository.delete(analysis.get());
        LOGGER.info("Analysis deleted");
        return "Analysis deleted successfully";
    }
}
