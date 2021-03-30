package com.warehouse.warehouse.service;

import com.warehouse.warehouse.model.Role;
import com.warehouse.warehouse.model.Users;
import com.warehouse.warehouse.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users createUser(Users users) {

        return userRepository.save(users);
    }
}
