package com.ravi.cache.cachestatistics.resource;


import com.ravi.cache.cachestatistics.dto.Users;
import com.ravi.cache.cachestatistics.service.UserCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user/v1")
public class UserResource {

    @Autowired
    private UserCacheService userCacheService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Users> getAllUsers(){

        Users users = userCacheService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }
}
