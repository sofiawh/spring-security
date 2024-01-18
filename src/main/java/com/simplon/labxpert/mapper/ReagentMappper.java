package com.simplon.labxpert.mapper;

import com.simplon.labxpert.model.dto.ReagentDTO;
import com.simplon.labxpert.model.entity.Reagent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReagentMappper extends GlobalMapper<ReagentDTO,Reagent>{
}
