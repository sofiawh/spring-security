package com.simplon.labxpert.service.impl;

import com.simplon.labxpert.mapper.UserMapper;
import com.simplon.labxpert.model.dto.UserDTO;
import com.simplon.labxpert.model.entity.User;
import com.simplon.labxpert.repository.UserRepository;
import com.simplon.labxpert.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.simplon.labxpert.exception.userException.UserNotFoundException;
import com.simplon.labxpert.exception.userException.UserAlreadyExistsException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<UserDTO> getUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return Optional.of(userMapper.toDTO(user));
    }

    @Override
    public Optional<UserDTO> getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        return Optional.of(userMapper.toDTO(user));
    }

    @Override
    public Optional<UserDTO> getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return Optional.of(userMapper.toDTO(user));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        validateUser(user);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    private void validateUser(User user) {
//        userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
//            throw new UserAlreadyExistsException("User already exists with this email");
//        });
//        userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
//            throw new UserAlreadyExistsException("User already exists with this username");
//        });
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO,long id) {
        User user = userMapper.toEntity(userDTO);
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        validateUser(user);
        existingUser = userRepository.save(user);
        return userMapper.toDTO(existingUser);
    }

    @Override
    public ResponseEntity<String> deleteUser(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>("User not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        userRepository.delete(userOptional.get());
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}