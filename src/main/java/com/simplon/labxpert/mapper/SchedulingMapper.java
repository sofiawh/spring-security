package com.simplon.labxpert.mapper;

import com.simplon.labxpert.model.dto.SchedulingDTO;
import com.simplon.labxpert.model.entity.Scheduling;
import org.mapstruct.Mapper;
/**
 * Mapper for the Scheduling entity.
 * It contains all the methods that we need to map a SchedulingDTO to a Scheduling and vice versa.
 */
@Mapper(componentModel = "spring")
public interface SchedulingMapper extends GlobalMapper<SchedulingDTO, Scheduling> {
}
