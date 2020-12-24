package com.ravi.cache.cachestatistics.service;

import com.ravi.cache.cachestatistics.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User createOrUpdateUser(User entity);

    void deleteUserById(Long id);
}
