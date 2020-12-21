package com.ravi.cache.cachestatistics.service.impl;

import com.ravi.cache.cachestatistics.dto.Users;
import com.ravi.cache.cachestatistics.service.UserCacheService;
import com.ravi.cache.cachestatistics.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import javax.cache.CacheManager;

import static com.ravi.cache.cachestatistics.constants.CacheConstants.USER_CACHE_ALIAS;
import static com.ravi.cache.cachestatistics.constants.CacheConstants.USER_CACHE_KEY;

@Component
@Slf4j
public class UserCacheServiceImpl implements UserCacheService {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public Users getAllUsers(){
        if(log.isDebugEnabled()){
            log.debug("START :: Getting all users");
        }
        Users users;
        Cache<String, Object> usersCache = cacheManager.getCache(USER_CACHE_ALIAS, String.class, Object.class);
        users = (Users)usersCache.get(USER_CACHE_KEY);
        if(null == users){
            if(log.isDebugEnabled()){
                log.debug("Data Not found in Cache", users);
                log.debug("Reading user information from service/DB");
            }
            users = userService.getUsers();
            usersCache.put(USER_CACHE_KEY, users);
        }
        if(log.isDebugEnabled()){
            log.debug("END :: Getting all users: {}", users);
        }
        return users;
    }

}

