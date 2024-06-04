package com.example.resource_tracker.data.mapper.user;

import com.example.resource_tracker.data.dto.user.UserDto;
import com.example.resource_tracker.data.mapper.BaseMapper;
import com.example.resource_tracker.data.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper extends BaseMapper<User, UserDto> {
}
