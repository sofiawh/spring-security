package com.simplon.labxpert.service.impl;

import com.simplon.labxpert.exception.handler.CustomNotFoundException;
import com.simplon.labxpert.mapper.SampleMapper;
import com.simplon.labxpert.model.dto.SampleDTO;
import com.simplon.labxpert.model.entity.Patient;
import com.simplon.labxpert.model.entity.Sample;
import com.simplon.labxpert.repository.PatientRepository;
import com.simplon.labxpert.repository.SampleRepository;
import com.simplon.labxpert.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SampleServiceImpl implements SampleService {

    private SampleMapper sampleMapper;
    private SampleRepository sampleRepository;
    private PatientRepository patientRepository;

    @Autowired
    private SampleServiceImpl(SampleMapper sampleMapper, SampleRepository sampleRepository) {
        this.sampleMapper = sampleMapper;
        this.sampleRepository = sampleRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<SampleDTO> getAllSimple() {
        List<Sample> samples = sampleRepository.findAll();
        return samples.stream()
                .map(sampleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SampleDTO createSample(SampleDTO sampleDTO) {
        //TODO i have  to find a Patient by this id :patientRepository.findByID(sampleDTO.getPatient().getPatientID())
        //TODO and also when adding it i have to add the  existing Patient to the sample
        Optional<Patient> optionalPatient = patientRepository.findById(sampleDTO.getPatientDTO().getPatientID());
        if (!optionalPatient.isPresent()) {
            throw new CustomNotFoundException("Patient not found", HttpStatus.BAD_REQUEST);
        }
        Sample sample = sampleMapper.toEntity(sampleDTO);
        sample.setPatient(optionalPatient.get());
        Sample savedSample = sampleRepository.save(sample);
        return sampleMapper.toDTO(savedSample);
    }

    @Override
    public SampleDTO getSampleById(long sampleId) {
        Optional<Sample> optionalSample = sampleRepository.findById(sampleId);
        if (optionalSample.isPresent()) {
            Sample sample = optionalSample.get();
            return sampleMapper.toDTO(sample);
        } else {
            throw new CustomNotFoundException("Sample not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteSample(long sampleId) {
        Optional<Sample> optionalSample = sampleRepository.findById(sampleId);
        if (!optionalSample.isPresent()) {
            throw new EntityNotFoundException("Sample not found with ID: " + sampleId);
        }
        sampleRepository.deleteById(sampleId);

    }


}
