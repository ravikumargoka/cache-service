package com.ravi.cache.cachestatistics.resource;

import com.ravi.cache.cachestatistics.entity.User;
import com.ravi.cache.cachestatistics.exception.RecordNotFoundException;
import com.ravi.cache.cachestatistics.manager.UserServiceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users/v1")
public class UserController {

    @Autowired
    UserServiceManager userServiceManager;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userEntities = userServiceManager.getAllUsers();
        return new ResponseEntity<>(userEntities, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userServiceManager.getUserById(id);
        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createOrUpdateUser(User user) throws RecordNotFoundException {
        User updated = userServiceManager.createOrUpdateUser(user);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUserById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        userServiceManager.deleteUserById(id);
        return HttpStatus.FORBIDDEN;
    }
}
