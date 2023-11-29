package com.example.msuser.mapper;

import com.example.msuser.dto.UserDto;
import com.example.msuser.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper instance = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    UserEntity toUser(UserDto userDto);
    UserDto toUserDto(UserEntity user);

}
