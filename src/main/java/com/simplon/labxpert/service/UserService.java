package com.simplon.labxpert.service;

import com.simplon.labxpert.model.dto.UserDTO;
import com.simplon.labxpert.model.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDTO> getUserById(long id);
    Optional<UserDTO> getUserByUsername(String username);
    Optional<UserDTO> getUserByEmail(String email);
    List<UserDTO> getAllUsers();
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO,long id);
    ResponseEntity<String> deleteUser(long id);

}
