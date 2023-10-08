package com.softuni.battleship.model.mapper;

import com.softuni.battleship.model.ShipEntity;
import com.softuni.battleship.model.dto.CreateShipDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShipMapper {
    @Mapping(target = "category", ignore = true)
    ShipEntity shipDtoToShipEntity(CreateShipDTO createShipDTO);
}
