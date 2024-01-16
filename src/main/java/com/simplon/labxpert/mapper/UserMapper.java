package com.simplon.labxpert.mapper;

import com.simplon.labxpert.model.dto.UserDTO;
import com.simplon.labxpert.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GlobalMapper<UserDTO, User>{
}
