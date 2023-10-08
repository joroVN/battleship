package com.softuni.battleship.service;

import com.softuni.battleship.model.CategoryEntity;
import com.softuni.battleship.model.ShipEntity;
import com.softuni.battleship.model.UserEntity;
import com.softuni.battleship.model.dto.CreateShipDTO;
import com.softuni.battleship.model.dto.ShipBattleDTO;
import com.softuni.battleship.model.dto.ShipDTO;
import com.softuni.battleship.model.mapper.ShipMapper;
import com.softuni.battleship.model.session.LoggedUser;
import com.softuni.battleship.repository.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final ShipMapper shipMapper;
    private final CategoryService categoryService;
    private final UserService userService;
    private final LoggedUser loggedUser;

    public ShipService(ShipRepository shipRepository, ShipMapper shipMapper, CategoryService categoryService, UserService userService, LoggedUser loggedUser) {
        this.shipRepository = shipRepository;
        this.shipMapper = shipMapper;
        this.categoryService = categoryService;
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    public boolean createShip(CreateShipDTO createShipDTO) {
        Optional<ShipEntity> shipOpt = this.shipRepository.findByName(createShipDTO.getName());

        if (shipOpt.isPresent()) {
            return false;
        }

        ShipEntity ship = this.shipMapper.shipDtoToShipEntity(createShipDTO);

        CategoryEntity category = this.categoryService.findCategoryEntity(createShipDTO.getCategory());
        ship.setCategory(category);

        UserEntity user = this.userService.findLoggedUser();
        ship.setUser(user);

        this.shipRepository.save(ship);

        return true;
    }

    public List<ShipDTO> allShips() {
        return this.shipRepository.findByOrderByNameAscHealthAscPowerAsc()
                .stream()
                .map(this::mapShipEntityToShipDto)
                .collect(Collectors.toList());
    }

    public List<ShipDTO> allShipsNotByUserId(Long id) {
        return this.shipRepository
                .findByUserIdNot(id)
                .stream()
                .map(this::mapShipEntityToShipDto)
                .collect(Collectors.toList());
    }

    public List<ShipDTO> allShipsByUserId(Long id) {
        return this.shipRepository
                .findByUserId(id)
                .stream()
                .map(this::mapShipEntityToShipDto)
                .collect(Collectors.toList());
    }


    private ShipDTO mapShipEntityToShipDto(ShipEntity ship) {
        return new ShipDTO()
                .setName(ship.getName())
                .setPower(ship.getPower())
                .setHealth(ship.getHealth())
                .setUserID(ship.getUser().getId());
    }


    public void battle(ShipBattleDTO shipBattleDTO) {
        String shipAttackerName = shipBattleDTO.getShipAttacker();
        String shipDefenderName = shipBattleDTO.getShipDefender();
        ShipEntity shipAttacker = this.shipRepository.findByName(shipAttackerName).get();
        ShipEntity shipDefender = this.shipRepository.findByName(shipDefenderName).get();

        shipDefender.setHealth(shipDefender.getHealth() - shipAttacker.getPower());

        if (shipDefender.getHealth() <= 0) {
            this.shipRepository.delete(shipDefender);
        } else {
            this.shipRepository.save(shipDefender);
        }

    }
}
