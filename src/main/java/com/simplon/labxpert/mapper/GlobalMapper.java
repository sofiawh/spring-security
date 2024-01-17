package com.simplon.labxpert.mapper;

/*
 * Global Mapper that map from entity to dto
 */
public interface GlobalMapper<DTO,ENTITY> {
        DTO toDTO(ENTITY e);
        ENTITY toEntity(DTO d);
}
