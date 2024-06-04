package com.example.concert_service.data.mapper.resource;

import com.example.concert_service.data.dto.resource.ResourceInfoDto;
import com.example.concert_service.data.mapper.BaseMapper;
import com.example.concert_service.data.model.Resource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ResourceInfoMapper extends BaseMapper<Resource, ResourceInfoDto> {
}
