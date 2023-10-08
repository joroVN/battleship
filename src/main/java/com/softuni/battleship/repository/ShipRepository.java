package com.softuni.battleship.repository;

import com.softuni.battleship.model.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long> {
    Optional<ShipEntity> findByName(String name);
    List<ShipEntity> findByOrderByNameAscHealthAscPowerAsc();
    List<ShipEntity> findByUserId(Long id);
    List<ShipEntity> findByUserIdNot(Long id);

}
