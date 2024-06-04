package com.example.resource_tracker.data.mapper.resource;

import com.example.resource_tracker.data.dto.resource.ResourceDto;
import com.example.resource_tracker.data.mapper.BaseMapper;
import com.example.resource_tracker.data.model.Resource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ResourceCreationMapper extends BaseMapper<Resource, ResourceDto> {
}
