package com.ravi.cache.statistics.manager;

import com.ravi.cache.statistics.entity.User;
import com.ravi.cache.statistics.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.cache.Cache;
import javax.cache.CacheManager;
import java.util.List;

import static com.ravi.cache.statistics.constants.CacheConstants.USERS_CACHE_ALIAS;
import static com.ravi.cache.statistics.constants.CacheConstants.USERS_CACHE_KEY;


@Component
@Slf4j
public class UserServiceManager {

    private final UserService userService;

    private final CacheManager cacheManager;

    public UserServiceManager(UserService userService, CacheManager cacheManager) {
        this.userService = userService;
        this.cacheManager = cacheManager;
    }

    public List<User> getAllUsers() {
        if (log.isDebugEnabled()) {
            log.debug("START :: Getting all users");
        }
        List<User> users;
        Cache<String, Object> usersCache = cacheManager.getCache(USERS_CACHE_ALIAS, String.class, Object.class);
        users = (List<User>) usersCache.get(USERS_CACHE_KEY);
        if (CollectionUtils.isEmpty(users)) {
            if (log.isDebugEnabled()) {
                log.debug("Data Not found in Cache");
                log.debug("Reading user information from service/DB");
            }
            users = userService.getAllUsers();
            usersCache.put(USERS_CACHE_KEY, users);
        }
        if (log.isDebugEnabled()) {
            log.debug("END :: Getting all users: {}", users);
        }
        return users;
    }

    public User getUserById(Long id) {
        if (log.isDebugEnabled()) {
            log.debug("START :: Getting user with id: {}", id);
        }
        User user;
        Cache<String, Object> usersCache = cacheManager.getCache(USERS_CACHE_ALIAS, String.class, Object.class);
        user = (User) usersCache.get(String.valueOf(id));
        if (null == user) {
            if (log.isDebugEnabled()) {
                log.debug("Data Not found in Cache  for user with id: {}", id);
                log.debug("Reading user information from service/DB");
            }
            user = userService.getUserById(id);
            usersCache.put(String.valueOf(id), user);
        }
        if (log.isDebugEnabled()) {
            log.debug("END :: Getting user with id: {}", id);
        }
        return user;
    }

    public User createOrUpdateUser(User entity) {
        if (log.isDebugEnabled()) {
            log.debug("START :: Create or update user: {}", entity);
        }
        User user;
        Cache<String, Object> usersCache = cacheManager.getCache(USERS_CACHE_ALIAS, String.class, Object.class);
        user = userService.createOrUpdateUser(entity);
        List<User> users = userService.getAllUsers();
        usersCache.put(USERS_CACHE_KEY, users);
        if (log.isDebugEnabled()) {
            log.debug("END :: Create or update user: {}", entity);
        }
        return user;
    }

    public void deleteUserById(Long id) {
        if (log.isDebugEnabled()) {
            log.debug("START :: Deleting user with id: {}", id);
        }
        userService.deleteUserById(id);
        Cache<String, Object> usersCache = cacheManager.getCache(USERS_CACHE_ALIAS, String.class, Object.class);
        List<User> users = userService.getAllUsers();
        usersCache.put(USERS_CACHE_KEY, users);
        if (log.isDebugEnabled()) {
            log.debug("END :: Deleting user with id: {}", id);
        }
    }
}
