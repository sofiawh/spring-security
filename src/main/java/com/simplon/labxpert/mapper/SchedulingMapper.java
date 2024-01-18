package com.simplon.labxpert.mapper;

import com.simplon.labxpert.model.dto.SchedulingDTO;
import com.simplon.labxpert.model.entity.Scheduling;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchedulingMapper extends GlobalMapper<SchedulingDTO, Scheduling> {
}
