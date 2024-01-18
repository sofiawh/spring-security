package com.simplon.labxpert.service;

import com.simplon.labxpert.model.dto.SampleDTO;

import java.util.List;

public interface SampleService {
    List<SampleDTO> getAllSimple();
    SampleDTO createSample(SampleDTO sample);
    SampleDTO getSampleById(long sampleId);
    void deleteSample(long sampleId);


}
