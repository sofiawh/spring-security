package com.simplon.labxpert.mapper;

import com.simplon.labxpert.model.dto.SampleDTO;
import com.simplon.labxpert.model.entity.Sample;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SampleMapper extends GlobalMapper<SampleDTO, Sample>{
}
