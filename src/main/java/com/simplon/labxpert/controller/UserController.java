package com.simplon.labxpert.controller;

import com.simplon.labxpert.model.dto.UserDTO;
import com.simplon.labxpert.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOS = userService.getAllUsers();
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id) {
        try {
            UserDTO userDTO = userService.getUserById(id).orElse(null);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        try {
            UserDTO userDTO = userService.getUserByUsername(username).orElse(null);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable long id, @Valid @RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUser = userService.updateUser(userDTO, id);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }
}