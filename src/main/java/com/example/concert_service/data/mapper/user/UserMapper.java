package com.example.concert_service.data.mapper.user;

import com.example.concert_service.data.dto.user.UserDto;
import com.example.concert_service.data.mapper.BaseMapper;
import com.example.concert_service.data.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper extends BaseMapper<User, UserDto> {
}
