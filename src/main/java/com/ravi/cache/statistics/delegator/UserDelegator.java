package com.ravi.cache.statistics.delegator;

import com.ravi.cache.statistics.dto.UserDTO;
import com.ravi.cache.statistics.entity.User;
import com.ravi.cache.statistics.manager.UserServiceManager;
import com.ravi.cache.statistics.mapper.UserDataMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDelegator {
    private final UserServiceManager userServiceManager;
    private final UserDataMapper userDataMapper;

    public UserDelegator(UserServiceManager userServiceManager, UserDataMapper userDataMapper) {
        this.userServiceManager = userServiceManager;
        this.userDataMapper = userDataMapper;
    }

    public List<UserDTO> getAllUsers() {
        return userDataMapper.mapToUserDTOList(userServiceManager.getAllUsers());
    }

    public UserDTO getUserById(Long id) {
        return userDataMapper.mapToUserDTO(userServiceManager.getUserById(id));
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = userDataMapper.mapToUser(userDTO);
        return userDataMapper.mapToUserDTO(userServiceManager.createUser(user));
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = userDataMapper.mapToUser(userDTO);
        return userDataMapper.mapToUserDTO(userServiceManager.updateUser(user));
    }

    public void deleteUserById(Long id) {
        userServiceManager.deleteUserById(id);
    }
}
