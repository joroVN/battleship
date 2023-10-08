package com.softuni.battleship.model.mapper;

import com.softuni.battleship.model.UserEntity;
import com.softuni.battleship.model.dto.UserRegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    UserEntity userDtoToUserEntity(UserRegisterDTO userRegisterDTO);
}
