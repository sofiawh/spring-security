package com.simplon.labxpert.service;

import com.simplon.labxpert.model.dto.UserDTO;
import com.simplon.labxpert.model.entity.User;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
/**
 * Interface for the User service.
 * It contains the methods that the service will implement.
 */
public interface UserService {
    public UserDTO getUserById(long id);

    UserDTO getUserByUsername(String username);

    UserDTO getUserByEmail(String email);

    List<UserDTO> getAllUsers();

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO,long id);
    ResponseEntity<String> deleteUser(long id);

}
