package com.simplon.labxpert.service.impl;

import com.simplon.labxpert.mapper.UserMapper;
import com.simplon.labxpert.model.entity.User;
import com.simplon.labxpert.repository.UserRepository;
import com.simplon.labxpert.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    public User getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

}
