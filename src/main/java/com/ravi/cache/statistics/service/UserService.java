package com.ravi.cache.statistics.service;

import com.ravi.cache.statistics.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User createUser(User entity);

    User updateUser(User entity);

    void deleteUserById(Long id);
}
