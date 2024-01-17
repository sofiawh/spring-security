package com.simplon.labxpert.mapper;

import com.simplon.labxpert.model.dto.PatientDTO;
import com.simplon.labxpert.model.entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper extends GlobalMapper<PatientDTO, Patient>{
}
