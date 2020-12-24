package com.ravi.cache.cachestatistics.service.impl;

import com.ravi.cache.cachestatistics.entity.User;
import com.ravi.cache.cachestatistics.exception.RecordNotFoundException;
import com.ravi.cache.cachestatistics.repository.UserRepository;
import com.ravi.cache.cachestatistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("No user record exists for the given id: " + id);
        }
    }

    public User createOrUpdateUser(User entity) {
        Optional<User> user = userRepository.findById(entity.getId());
        if (user.isPresent()) {
            User newEntity = user.get();
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
            newEntity.setEmail(entity.getEmail());
            newEntity = userRepository.save(newEntity);
            return newEntity;
        } else {
            entity = userRepository.save(entity);
            return entity;
        }
    }

    public void deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No user record exists for the given id: " + id);
        }
    }


}
