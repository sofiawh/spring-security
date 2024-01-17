package com.simplon.labxpert.service.impl;

import com.simplon.labxpert.exception.customException.CustomNotFoundException;
import com.simplon.labxpert.mapper.UserMapper;
import com.simplon.labxpert.model.dto.UserDTO;
import com.simplon.labxpert.model.entity.User;
import com.simplon.labxpert.repository.UserRepository;
import com.simplon.labxpert.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String USER_NOT_FOUND = "User not found with id: ";
    private UserRepository userRepository;
    private UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO getUserById(long id){
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new CustomNotFoundException(USER_NOT_FOUND + id, HttpStatus.NOT_FOUND);
        }else {
            return userMapper.toDTO(user.get());
        }
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new CustomNotFoundException("User not found with username: " + username, HttpStatus.NOT_FOUND);
        }else {
            return userMapper.toDTO(user.get());
        }
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new CustomNotFoundException("User not found with email: " + email, HttpStatus.NOT_FOUND);
        }else {
            return userMapper.toDTO(user.get());
        }
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
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if (userOptional.isPresent()) {
            throw new CustomNotFoundException("Username already exists", HttpStatus.BAD_REQUEST);
        }
        userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new CustomNotFoundException("Email already exists", HttpStatus.BAD_REQUEST);
        }
    }
    // TODO : @Ayoub : i should check the update if works fine alsso add author to the project worling on logger and javaDoc
    @Override
    public UserDTO updateUser(UserDTO userDTO,long id) {
        User user = userMapper.toEntity(userDTO);
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new CustomNotFoundException(USER_NOT_FOUND + id, HttpStatus.NOT_FOUND);
        }
        validateUser(user);
        user.setUserID(id);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    @Override
    public ResponseEntity<String> deleteUser(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new CustomNotFoundException(USER_NOT_FOUND + id, HttpStatus.NOT_FOUND);
        }
        userRepository.delete(userOptional.get());
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}