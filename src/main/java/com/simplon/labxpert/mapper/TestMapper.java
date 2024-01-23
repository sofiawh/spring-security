package com.simplon.labxpert.mapper;

import com.simplon.labxpert.model.dto.TestDTO;
import com.simplon.labxpert.model.entity.Test;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestMapper extends GlobalMapper<TestDTO, Test> {
}