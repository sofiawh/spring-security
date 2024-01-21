package com.simplon.labxpert.mapper;

import com.simplon.labxpert.model.dto.SampleDTO;
import com.simplon.labxpert.model.entity.Sample;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
/**
 * Mapper for the Sample entity.
 * It contains all the methods that we need to map a SampleDTO to a Sample and vice versa.
 */
@Mapper(componentModel = "spring", uses = {PatientMapper.class})
public interface SampleMapper{
    @Mapping(target = "patientDTO", source = "patient")
    SampleDTO toDTO(Sample sample);

    @Mapping(target = "patient", source = "patientDTO")
    Sample toEntity(SampleDTO sampleDTO);
}
