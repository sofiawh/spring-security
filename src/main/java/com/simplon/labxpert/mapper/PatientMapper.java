package com.simplon.labxpert.mapper;

import com.simplon.labxpert.model.dto.PatientDTO;
import com.simplon.labxpert.model.entity.Patient;
import org.mapstruct.Mapper;
/**
 * Mapper for the Patient entity.
 * It contains all the methods that we need to map a PatientDTO to a Patient and vice versa.
 */
@Mapper(componentModel = "spring")
public interface PatientMapper extends GlobalMapper<PatientDTO, Patient>{
}
