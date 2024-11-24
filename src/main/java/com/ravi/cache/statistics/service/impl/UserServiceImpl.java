package com.ravi.cache.statistics.service.impl;

import com.ravi.cache.statistics.entity.User;
import com.ravi.cache.statistics.exception.RecordNotFoundException;
import com.ravi.cache.statistics.repository.UserRepository;
import com.ravi.cache.statistics.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("No user record exists for the given id: " + id);
        }
    }

    public User createUser(User entity) {
        return userRepository.save(entity);
    }

    public User updateUser(User entity) {
        Optional<User> user = userRepository.findById(entity.getId());
        User newEntity = null;
        if (user.isPresent()) {
            newEntity = user.get();
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
            newEntity.setEmail(entity.getEmail());
            newEntity = userRepository.save(newEntity);
        }
        return newEntity;
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
