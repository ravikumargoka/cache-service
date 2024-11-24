package com.ravi.cache.statistics.mapper;

import com.ravi.cache.statistics.dto.UserDTO;
import com.ravi.cache.statistics.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDataMapper {

    UserDataMapper INSTANCE = Mappers.getMapper(UserDataMapper.class);

    UserDTO mapToUserDTO(User user);

    List<UserDTO> mapToUserDTOList(List<User> users);

    User mapToUser(UserDTO userDTO);

    List<User> mapToUserList(List<UserDTO> userDTO);
}