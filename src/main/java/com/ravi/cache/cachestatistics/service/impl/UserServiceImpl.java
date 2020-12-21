package com.ravi.cache.cachestatistics.service.impl;

import com.ravi.cache.cachestatistics.dto.User;
import com.ravi.cache.cachestatistics.dto.Users;
import com.ravi.cache.cachestatistics.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public Users getUsers(){
        if(log.isDebugEnabled()){
            log.debug("START :: Getting all users");
        }
        User user1 = new User(1, "John", "Smith");
        User user2 = new User(2, "Brian", "McMillan");
        User user3 = new User(3, "Mark", "Robinson Jr.");
        User user4 = new User(4, "Bill", "Meyer");
        User user5 = new User(5, "Sara", "Williamson");
        List<User> usersList = new ArrayList<>();
        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);
        usersList.add(user4);
        usersList.add(user5);

        Users users = new Users();
        users.setUsers(usersList);
        if(log.isDebugEnabled()){
            log.debug("END :: Getting all users: {}", users);
        }
        return users;
    }
}
